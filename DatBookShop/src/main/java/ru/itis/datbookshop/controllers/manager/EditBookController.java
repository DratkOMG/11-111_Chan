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

@WebServlet(name = "EditBookController", value = "/edit_book_controller")
public class EditBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        booksService.updateBook(request);

        Book book = booksService.findBookById(Long.parseLong(request.getParameter("bid")));
        request.setAttribute("book", book);

        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");
        List<Categories> categoriesList = categoriesService.findAll();
        request.setAttribute("listCategory", categoriesList);


        getServletContext().getRequestDispatcher("/WEB-INF/managerViews/editProduct.jsp").forward(request, response);
    }
}
