package ru.itis.datbookshop.controllers.home;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Order;
import ru.itis.datbookshop.services.user.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GlobalHistoryController", value = "/global_history_controller")
public class GlobalHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = (OrderService) getServletContext().getAttribute("order_service");

        List<Order> orderList = orderService.findAllOrder();

        System.out.println(orderList);
        System.out.println(orderList.get(0).getPrice());
        System.out.println(orderList.get(0).getUserId());

        request.setAttribute("listOrders", orderList);
        request.setAttribute("global", true);

        getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
