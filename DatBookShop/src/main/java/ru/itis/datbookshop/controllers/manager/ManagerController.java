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

@WebServlet(name = "ManagerController", value = "/manager_controller")
public class ManagerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        request.setAttribute("bookService", booksService);
        HttpSession session = request.getSession(false);

        long id = (long) session.getAttribute("root_id");
        List<Book> bookList = booksService.findTop10BySellerId(id);
        request.setAttribute("listBook", bookList);

        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");

        List<Categories> categoriesList = categoriesService.findAll();
        request.setAttribute("listCategory", categoriesList);

        getServletContext().getRequestDispatcher("/WEB-INF/managerViews/managerProduct.jsp").forward(request, response);
    }
}
