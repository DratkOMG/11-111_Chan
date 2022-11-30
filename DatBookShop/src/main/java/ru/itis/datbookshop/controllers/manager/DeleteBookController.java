package ru.itis.datbookshop.controllers.manager;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.services.product.BooksService;

import java.io.IOException;

@WebServlet(name = "DeleteBookController", value = "/delete_book_controller")
public class DeleteBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        booksService.deleteBook(request);
        getServletContext().getRequestDispatcher("/manager_controller").forward(request, response);
    }
}
