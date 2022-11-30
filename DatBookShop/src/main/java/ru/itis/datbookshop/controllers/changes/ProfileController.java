package ru.itis.datbookshop.controllers.changes;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.services.user.UserService;

import java.io.IOException;

@WebServlet(name = "ProfileController", value = "/profile_controller")
public class ProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        UserService userService = (UserService) getServletContext().getAttribute("user_service");
        User user = userService.findUserById((Long) session.getAttribute("root_id"));
        request.setAttribute("userProfile", user);

        Cookie[] cookies = request.getCookies();

        int countProduct = 0;
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("BookAdded")) {
                countProduct++;
            }
        }

        request.setAttribute("countProduct", countProduct);

        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}
