package ru.itis.datbookshop.controllers.admins;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.user.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeProductsController", value = "/employee_products_controller")
public class EmployeeProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long eId = Long.parseLong(request.getParameter("eid"));

        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        List<Book> bookList = booksService.findAllBySellerId(eId);

        UserService userService = (UserService) getServletContext().getAttribute("user_service");
        String sellersName = userService.findUserById(eId).getUserName();
        request.setAttribute("listBook", bookList);
        request.setAttribute("sellers_name", sellersName);
        getServletContext().getRequestDispatcher("/WEB-INF/adminViews/employeeProductManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
