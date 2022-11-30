package ru.itis.datbookshop.controllers.changes;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.services.user.UserService;

import java.io.IOException;

@WebServlet(name = "ChangeProfileController", value = "/change_profile_controller")
public class ChangeProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        long uId = (long) session.getAttribute("root_id");
        try {
            int money = Integer.parseInt(request.getParameter("donate"));
            if (money <= 0) {
                throw new NumberFormatException();
            }
            UserService userService = (UserService) getServletContext().getAttribute("user_service");
            userService.donate(money, uId);
            request.setAttribute("niceDonate", "Done");
        } catch (NumberFormatException e) {
            request.setAttribute("errorDonate", "???");
        }
        getServletContext().getRequestDispatcher("/profile_controller").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("user_service");
        userService.changeProfile(request);

        getServletContext().getRequestDispatcher("/profile_controller").forward(request, response);
    }
}
