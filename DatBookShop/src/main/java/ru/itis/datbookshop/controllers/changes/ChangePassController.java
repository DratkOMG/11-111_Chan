package ru.itis.datbookshop.controllers.changes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datbookshop.services.user.impl.AccountServiceImpl;

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
        AccountServiceImpl accountServiceImpl = (AccountServiceImpl) getServletContext().getAttribute("account_service");
        accountServiceImpl.changePassword(request);
        getServletContext().getRequestDispatcher("/profile_controller").forward(request, response);
    }
}
