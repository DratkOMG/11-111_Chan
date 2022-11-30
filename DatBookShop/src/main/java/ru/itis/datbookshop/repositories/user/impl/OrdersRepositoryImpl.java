package ru.itis.datbookshop.repositories.user.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Order;
import ru.itis.datbookshop.repositories.Repository;
import ru.itis.datbookshop.repositories.user.OrdersRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersRepositoryImpl extends Repository implements OrdersRepository {
    private final RowMapper<Order> orderRowMapper = (rs, rowNum) ->
            Order.builder()
                    .orderId(rs.getLong("order_id"))
                    .date(rs.getTimestamp("date"))
                    .userId(rs.getLong("user_id"))
                    .userName(rs.getString("user_name"))
                    .price(rs.getInt("price"))
                    .build();

    private final RowMapper<Book> bookRowMapper = ((rs, rowNum) ->
            Book.builder()
                    .title(rs.getString("title"))
                    .image(rs.getString("image"))
                    .price(rs.getInt("price"))
                    .quantitySold(rs.getInt("quantity"))
                    .build());

    @Override
    public Long addOrder(Order order) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", order.getDate());
        params.put("price", order.getPrice());
        params.put("user_id", order.getUserId());
        params.put("user_name", order.getUserName());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate());

        return insert.withTableName("orders")
                .usingGeneratedKeyColumns("order_id")
                .executeAndReturnKey(new MapSqlParameterSource(params)).longValue();

    }

    @Override
    public void addOrderInformation(long orderId, List<Book> bookList, List<Integer> quantity) {
        String INSERT_INFORMATION_TO_ORDER = "insert into order_information(order_id, title, image, price, quantity)\n" +
                "values (:order_id, :title, :image, :price, :quantity)";

        String UPDATE_SOLD_BOOK = "update book\n" +
                "set quantity_sold = quantity_sold + :amount\n" +
                "where book_id = :bookId;";

        for (int i = 0; i < bookList.size(); i++) {
            Map<String, Object> paramsForOrder = new HashMap<>();
            paramsForOrder.put("order_id", orderId);
            paramsForOrder.put("title", bookList.get(i).getTitle());
            paramsForOrder.put("image", bookList.get(i).getImage());
            paramsForOrder.put("quantity", quantity.get(i));
            paramsForOrder.put("price", (bookList.get(i).getPrice() * quantity.get(i)));

            namedParameterJdbcTemplate.update(INSERT_INFORMATION_TO_ORDER, paramsForOrder);

            Map<String, Object> paramsForBook = new HashMap<>();
            paramsForBook.put("bookId", bookList.get(i).getId());
            paramsForBook.put("amount", quantity.get(i));

            namedParameterJdbcTemplate.update(UPDATE_SOLD_BOOK, paramsForBook);
        }
    }

    @Override
    public List<Order> getAllOrderById(long root_id) {
        String SELECT_ORDERS_BY_ID = "select * from orders where user_id = :id";

        return namedParameterJdbcTemplate.query(SELECT_ORDERS_BY_ID,
                Collections.singletonMap("id", root_id),
                orderRowMapper);
    }

    @Override
    public List<Book> getInformationOrderByOrderId(long uId) {
        String SELECT_INFORMATION_BY_ID = "select * from order_information where order_id = :id";

        return namedParameterJdbcTemplate.query(SELECT_INFORMATION_BY_ID,
                Collections.singletonMap("id", uId),
                bookRowMapper);
    }

    @Override
    public List<Order> getAllOrder() {
        String SELECT_ALL_ORDER = "select * from orders order by order_id";

        return namedParameterJdbcTemplate.query(SELECT_ALL_ORDER, orderRowMapper);
    }
}
