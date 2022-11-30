package ru.itis.datbookshop.services.user;

import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Order;

import java.util.List;

public interface OrderService {
    Long saveOrder(Order order);

    void saveInformationOrder(long orderId, List<Book> bookList, List<Integer> quantity);

    List<Order> findAllOrderById(long root_id);

    List<Book> findInformationOrderByOrderId(long uId);

    List<Order> findAllOrder();
}
