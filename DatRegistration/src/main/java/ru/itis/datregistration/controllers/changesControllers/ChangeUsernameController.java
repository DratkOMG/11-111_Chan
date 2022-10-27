package ru.itis.datregistration.controllers.changesControllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datregistration.repositories.impl.UsersRepositoryImpl;
import ru.itis.datregistration.services.UserService;
import ru.itis.datregistration.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "ChangeUsernameController", value = "/change_username_controller")
public class ChangeUsernameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        UserService userService = new UserServiceImpl(new UsersRepositoryImpl());
        userService.changeUsername(request);
        getServletContext().getRequestDispatcher("/WEB-INF/views/changesViews/change-profile.jsp").forward(request, response);

    }
}
