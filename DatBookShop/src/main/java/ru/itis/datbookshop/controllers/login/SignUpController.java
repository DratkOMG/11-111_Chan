package ru.itis.datbookshop.controllers.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datbookshop.services.user.impl.AccountServiceImpl;

import java.io.IOException;

@WebServlet(name = "SignUpController", value = "/sign_up_controller")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login/signup.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountServiceImpl accountServiceImpl = (AccountServiceImpl) getServletContext().getAttribute("account_service");
        accountServiceImpl.signUp(request);
        getServletContext().getRequestDispatcher("/WEB-INF/login/signup.jsp").forward(request, response);



    }
}
