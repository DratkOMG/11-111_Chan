package ru.itis.datbookshop.controllers.home.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.services.product.BooksService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateCartController", value = "/update_cart_controller")
public class UpdateCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = "BookAdded" + request.getParameter("bid");

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(bookId)) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        response.sendRedirect("load_cart_controller");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amount = request.getParameter("amount");
        String bookId = "BookAdded" + request.getParameter("bid");
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        List<Book> bookList = new ArrayList<>();

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(bookId)) {
                cookie.setValue(amount);
                response.addCookie(cookie);
            }
            if (cookie.getName().startsWith("BookAdded")) {
                Book book = booksService.findBookById(Long.parseLong(cookie.getName().substring(9)));
                try {
                    book.setQuantitySold(Integer.parseInt(cookie.getValue()));
                } catch (NumberFormatException e){
                    book.setQuantitySold(0);
                }
                bookList.add(book);
            }
        }

        int total = 0;
        for (Book book : bookList) {
            total += book.getPrice() * book.getQuantitySold();
        }


        response.getWriter().println("<a id=\"sure\" class=\"btn btn-primary btn-block btn-lg\" href=\"/DatBookShop_war_exploded/load_cart_controller\">\n" +
                "                                        <div class=\"d-flex justify-content-between\">\n" +
                "                                            <span>Are you sure?</span>\n" +
                "                                            <span id=\"check-out\">$" + total + "</span>\n" +
                "                                        </div>\n" +
                "                                    </a>");
    }
}
