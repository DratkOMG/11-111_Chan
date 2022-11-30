package ru.itis.datbookshop.controllers.manager;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Categories;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.product.CategoriesService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadBookController", value = "/load_book_controller")
public class LoadBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bid");
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        Book book = booksService.findBookById(Long.parseLong(bookId));
        request.setAttribute("book", book);

        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");

        List<Categories> categoriesList = categoriesService.findAll();
        request.setAttribute("listCategory", categoriesList);

        getServletContext().getRequestDispatcher("/WEB-INF/managerViews/editProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
