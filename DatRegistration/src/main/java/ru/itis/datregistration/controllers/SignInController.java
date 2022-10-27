package ru.itis.datregistration.controllers;

import ru.itis.datregistration.repositories.impl.AccountsRepositoryImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datregistration.repositories.impl.UsersRepositoryImpl;
import ru.itis.datregistration.services.AccountService;
import ru.itis.datregistration.services.impl.AccountServiceImpl;

import java.io.IOException;

@WebServlet(name = "SignInController", value = "/sign_in_controller")
public class SignInController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("root_email");
        session.removeAttribute("root_password");
        getServletContext().getRequestDispatcher("/WEB-INF/views/loginsViews/sign-in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        if (session.getAttribute("root_email") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/main-page.jsp").forward(request, response);
        }

        AccountService accountService = new AccountServiceImpl(new AccountsRepositoryImpl(), new UsersRepositoryImpl());

        if (accountService.signIn(request)) {
            session.setAttribute("root_email", request.getParameter("email"));
            session.setAttribute("root_password", request.getParameter("password"));
            getServletContext().getRequestDispatcher("/WEB-INF/views/main-page.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/loginsViews/sign-in.jsp").forward(request, response);
        }

    }
}
