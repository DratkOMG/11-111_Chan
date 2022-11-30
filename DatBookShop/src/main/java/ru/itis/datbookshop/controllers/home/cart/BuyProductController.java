package ru.itis.datbookshop.controllers.home.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Order;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.user.OrderService;
import ru.itis.datbookshop.services.user.UserService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BuyProductController", value = "/buy_product_controller")
public class BuyProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        int total = -Integer.parseInt(request.getParameter("total"));
        long uId = (long) session.getAttribute("root_id");

        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        UserService userService = (UserService) getServletContext().getAttribute("user_service");
        OrderService orderService = (OrderService) getServletContext().getAttribute("order_service");
        User user = userService.findUserById(uId);

        if (user.getBalance() < (-total)) {
            request.setAttribute("errorBuy", "Money??");
            getServletContext().getRequestDispatcher("/load_cart_controller").forward(request, response);
        } else {
            userService.donate(total, uId);

            Order order = Order.builder()
                    .date(new Timestamp(System.currentTimeMillis()))
                    .price(-total)
                    .userId(uId)
                    .userName(user.getUserName())
                    .build();
            long orderId = orderService.saveOrder(order);

            List<Book> bookList = new ArrayList<>();
            List<Integer> quantity = new ArrayList<>();

            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("BookAdded")) {
                    bookList.add(booksService.findBookById(Long.parseLong(cookie.getName().substring(9))));
                    quantity.add(Integer.valueOf(cookie.getValue()));
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            orderService.saveInformationOrder(orderId, bookList, quantity);
        }

        response.sendRedirect("home_controller");
    }
}
