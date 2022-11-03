package ru.itis.datregistration.controllers.findControllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datregistration.models.User;
import ru.itis.datregistration.services.UserService;

import java.io.IOException;

@WebServlet(name = "FindByNumberPhone", value = "/find_by_number_phone")
public class FindByNumberPhone extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/findsViews/find-by-number-phone.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");

        UserService userService = (UserService) getServletContext().getAttribute("user_service");
        User result = userService.findByNumberPhone(Long.parseLong(search));

        if (result == null) {
            request.setAttribute("result", "No search result");
            getServletContext().getRequestDispatcher("/WEB-INF/views/findsViews/find-by-number-phone.jsp").forward(request, response);
        } else {
            request.setAttribute("result" , result);
            getServletContext().getRequestDispatcher("/WEB-INF/views/findsViews/find-by-number-phone.jsp").forward(request, response);
        }
    }
}
