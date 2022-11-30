package ru.itis.datbookshop.controllers.manager;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Categories;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.product.CategoriesService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddBookController", value = "/add_book_controller")
public class AddBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");
        List<Categories> categoriesList = categoriesService.findAll();
        request.setAttribute("listCategory", categoriesList);

        getServletContext().getRequestDispatcher("/WEB-INF/managerViews/addProduct.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        booksService.addBook(request);
        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");
        List<Categories> categoriesList = categoriesService.findAll();
        request.setAttribute("listCategory", categoriesList);

        getServletContext().getRequestDispatcher("/manager_controller").forward(request, response);
    }
}
