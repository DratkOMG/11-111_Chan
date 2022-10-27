package ru.itis.datregistration.controllers.findControllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datregistration.repositories.impl.UsersRepositoryImpl;
import ru.itis.datregistration.models.User;
import ru.itis.datregistration.services.UserService;
import ru.itis.datregistration.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "FindByUsername", value = "/find_by_username")
public class FindByUsername extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/findsViews/find-by-username.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");

        UserService userService = new UserServiceImpl(new UsersRepositoryImpl());
        User result = userService.findByUsername(search);

        if (result == null) {
            request.setAttribute("result", "No search result");
            getServletContext().getRequestDispatcher("/WEB-INF/views/findsViews/find-by-username.jsp").forward(request, response);
        } else {
            request.setAttribute("result", result);
            getServletContext().getRequestDispatcher("/WEB-INF/views/findsViews/find-by-username.jsp").forward(request, response);
        }

    }
}
