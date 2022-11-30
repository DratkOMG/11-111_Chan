package ru.itis.datbookshop.controllers.home;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Order;
import ru.itis.datbookshop.services.user.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PurchaseHistoryController", value = "/purchase_history_controller")
public class PurchaseHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("oid") != null) {
            doPost(request, response);
        } else {
            OrderService orderService = (OrderService) getServletContext().getAttribute("order_service");
            HttpSession session = request.getSession(false);

            List<Order> orderList = orderService.findAllOrderById((long) session.getAttribute("root_id"));

            request.setAttribute("listOrders", orderList);

            getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long uId = Long.parseLong(request.getParameter("oid"));

        OrderService orderService = (OrderService) getServletContext().getAttribute("order_service");

        List<Book> bookList = orderService.findInformationOrderByOrderId(uId);

        request.setAttribute("informations", bookList);
        getServletContext().getRequestDispatcher("/WEB-INF/views/orderInformation.jsp").forward(request, response);
    }
}
