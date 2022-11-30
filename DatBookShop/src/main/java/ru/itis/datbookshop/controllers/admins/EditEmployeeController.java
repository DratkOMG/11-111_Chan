package ru.itis.datbookshop.controllers.admins;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Account;
import ru.itis.datbookshop.services.user.AccountService;
import ru.itis.datbookshop.services.user.UserService;

import java.io.IOException;

@WebServlet(name = "EditEmployeeController", value = "/edit_employee_controller")
public class EditEmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long eId = Long.parseLong(request.getParameter("eid"));
        String seller = request.getParameter("is_seller");

        AccountService accountService = (AccountService) getServletContext().getAttribute("account_service");
        accountService.updateSeller(eId, seller);

        Account account = accountService.findAccountById(eId);
        request.setAttribute("account", account);
        request.setAttribute("mess", "Saved");

        getServletContext().getRequestDispatcher("/admins_manager_controller").forward(request, response);

    }
}
