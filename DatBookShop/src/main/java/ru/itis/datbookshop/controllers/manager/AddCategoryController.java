package ru.itis.datbookshop.controllers.manager;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.services.product.CategoriesService;

import java.io.IOException;

@WebServlet(name = "AddCategoryController", value = "/add_category_controller")
public class AddCategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");

        if (!category.isEmpty() && !category.isBlank()) {
            categoriesService.addCategory(category);
        }

        response.sendRedirect("manager_controller");
    }
}
