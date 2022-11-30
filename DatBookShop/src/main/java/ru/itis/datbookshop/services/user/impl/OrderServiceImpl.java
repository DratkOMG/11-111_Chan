package ru.itis.datbookshop.services.user.impl;

import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Order;
import ru.itis.datbookshop.repositories.user.OrdersRepository;
import ru.itis.datbookshop.services.user.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Long saveOrder(Order order) {
        return ordersRepository.addOrder(order);
    }

    @Override
    public void saveInformationOrder(long orderId, List<Book> bookList, List<Integer> quantity) {
        ordersRepository.addOrderInformation(orderId, bookList, quantity);
    }

    @Override
    public List<Order> findAllOrderById(long root_id) {
        return ordersRepository.getAllOrderById(root_id);
    }

    @Override
    public List<Book> findInformationOrderByOrderId(long uId) {
        return ordersRepository.getInformationOrderByOrderId(uId);
    }

    @Override
    public List<Order> findAllOrder() {
        return ordersRepository.getAllOrder();
    }
}
