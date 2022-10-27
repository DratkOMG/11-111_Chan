package ru.itis.datregistration.controllers.changesControllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datregistration.repositories.impl.AccountsRepositoryImpl;
import ru.itis.datregistration.repositories.impl.UsersRepositoryImpl;
import ru.itis.datregistration.services.impl.AccountServiceImpl;

import java.io.IOException;

@WebServlet(name = "ChangePassController", value = "/change_pass_controller")
public class ChangePassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/changesViews/change-password.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        AccountServiceImpl userService = new AccountServiceImpl(new AccountsRepositoryImpl(), new UsersRepositoryImpl());
        userService.changePassword(request);
        if (request.getAttribute("mess").equals("Change password successful")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/changesViews/change-pass-successful.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/changesViews/change-password.jsp").forward(request, response);
        }



    }
}
