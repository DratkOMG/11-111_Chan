package ru.itis.datregistration.controllers;

import ru.itis.datregistration.repositories.impl.AccountsRepositoryImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datregistration.repositories.impl.UsersRepositoryImpl;
import ru.itis.datregistration.services.impl.AccountServiceImpl;

import java.io.IOException;

@WebServlet(name = "SignUpController", value = "/sign_up_controller")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/loginsViews/sign-up.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        AccountServiceImpl userService = new AccountServiceImpl(new AccountsRepositoryImpl(), new
                UsersRepositoryImpl());
        userService.signUp(request);
        getServletContext().getRequestDispatcher("/WEB-INF/views/loginsViews/sign-up.jsp").forward(request, response);


    }
}
