package ru.itis.datbookshop.controllers.home.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.services.product.BooksService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoadCartController", value = "/load_cart_controller")
public class LoadCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        List<Book> bookList = new ArrayList<>();

        int countProduct = 0;
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("BookAdded")) {
                Book book = booksService.findBookById(Long.parseLong(cookie.getName().substring(9)));
                try {
                    book.setQuantitySold(Integer.parseInt(cookie.getValue()));
                } catch (NumberFormatException e){
                    book.setQuantitySold(0);
                }
                bookList.add(book);
                countProduct++;
            }
        }
        int total = 0;
        for (Book book: bookList) {
            total += book.getPrice() * book.getQuantitySold();
        }
        request.setAttribute("total", total);
        request.setAttribute("listBookAdded", bookList);
        request.setAttribute("countProduct", countProduct);
        getServletContext().getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}
