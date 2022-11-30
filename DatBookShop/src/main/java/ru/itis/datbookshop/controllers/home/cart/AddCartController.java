package ru.itis.datbookshop.controllers.home.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddCartController", value = "/add_cart_controller")
public class AddCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = "BookAdded" + request.getParameter("bid");

        int count = addBookToCart(request, response, bookId);

        response.getWriter().println(count);

        response.sendRedirect("load_cart_controller");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = "BookAdded" + request.getParameter("bookId");

        int count = addBookToCart(request, response, bookId);

        response.getWriter().println(count);
    }

    private static int addBookToCart(HttpServletRequest request, HttpServletResponse response, String bookId) {
        Cookie[] cookies = request.getCookies();
        int count = 0;

        boolean cookieExist = false;
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(bookId)) {
                cookie.setValue(String.valueOf(Integer.parseInt(cookie.getValue()) + 1));
                cookieExist = true;
                response.addCookie(cookie);
            }

            if (cookie.getName().startsWith("BookAdded")) {
                count++;
            }
        }
        if (!cookieExist) {
            Cookie cookieBook = new Cookie(bookId, "1");
            count++;
            response.addCookie(cookieBook);
        }
        return count;
    }
}
