package ru.itis.datbookshop.controllers.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.itis.datbookshop.services.user.AccountService;

import java.io.IOException;

@WebServlet(name = "SignInController", value = "/sign_in_controller")
public class SignInController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (session.getAttribute("root_id") != null) {
            session.removeAttribute("root_id");
            getServletContext().getRequestDispatcher("/home_controller").forward(request, response);
        } else {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("acc")) {
                    request.setAttribute("email", cookie.getValue());
                }
                if (cookie.getName().equals("pass")) {
                    request.setAttribute("pass", cookie.getValue());
                }
            }
            getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        AccountService accountService = (AccountService) getServletContext().getAttribute("account_service");

        if (accountService.signIn(request, response)) {
            session.setAttribute("root_id", accountService.findAccountByEmail(request.getParameter("email")).getAccountId());
            session.setAttribute("admin", request.getAttribute("ad"));
            session.setAttribute("seller", request.getAttribute("sell"));
            request.removeAttribute("ad");
            request.removeAttribute("sell");
            response.sendRedirect("home_controller");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }
    }
}
