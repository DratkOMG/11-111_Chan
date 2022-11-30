package ru.itis.datbookshop.controllers.admins;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Account;
import ru.itis.datbookshop.services.user.AccountService;

import java.io.IOException;

@WebServlet(name = "DeleteEmployeeController", value = "/delete_employee_controller")
public class DeleteEmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long eId = Long.parseLong(request.getParameter("eid"));

        AccountService accountService = (AccountService) getServletContext().getAttribute("account_service");
        accountService.deleteById(eId);

        getServletContext().getRequestDispatcher("/admins_manager_controller").forward(request, response);

    }
}
