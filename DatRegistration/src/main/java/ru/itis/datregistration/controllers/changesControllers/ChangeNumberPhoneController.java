package ru.itis.datregistration.controllers.changesControllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datregistration.services.UserService;

import java.io.IOException;

@WebServlet(name = "ChangeNumberPhoneController", value = "/change_number_phone_controller")
public class ChangeNumberPhoneController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("user_service");
        userService.changeNumberPhone(request);
        getServletContext().getRequestDispatcher("/WEB-INF/views/changesViews/change-profile.jsp").forward(request, response);
    }
}
