package ru.itis.datbookshop.controllers.admins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.services.user.AccountService;
import ru.itis.datbookshop.services.user.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminsManagerController", value = "/admins_manager_controller")
public class AdminsManagerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("user_service");

        List<User> users = userService.findAll();

        request.setAttribute("listEmployees", users);
        getServletContext().getRequestDispatcher("/WEB-INF/adminViews/managerEmployees.jsp").forward(request, response);

    }
}
