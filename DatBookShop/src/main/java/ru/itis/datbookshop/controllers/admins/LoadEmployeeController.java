package ru.itis.datbookshop.controllers.admins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datbookshop.models.Account;
import ru.itis.datbookshop.services.user.AccountService;

import java.io.IOException;

@WebServlet(name = "LoadEmployeeController", value = "/load_employee_controller")
public class LoadEmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("eid");

        AccountService accountService = (AccountService) getServletContext().getAttribute("account_service");
        Account account = accountService.findAccountById(Long.parseLong(employeeId));

        request.setAttribute("account", account);

        getServletContext().getRequestDispatcher("/WEB-INF/adminViews/editEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
