package ru.itis.datbookshop.repositories.user;

import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Order;

import java.util.Date;
import java.util.List;

public interface OrdersRepository {
    Long addOrder(Order order);

    void addOrderInformation(long orderId, List<Book> bookList, List<Integer> quantity);

    List<Order> getAllOrderById(long root_id);

    List<Book> getInformationOrderByOrderId(long uId);

    List<Order> getAllOrder();
}
