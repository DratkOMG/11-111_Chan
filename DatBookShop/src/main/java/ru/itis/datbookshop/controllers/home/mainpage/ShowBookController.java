package ru.itis.datbookshop.controllers.home.mainpage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.product.CategoriesService;
import ru.itis.datbookshop.services.user.UserService;
import ru.itis.datbookshop.show.LeftShow;

import java.io.IOException;

@WebServlet(name = "ShowBookController", value = "/show_book_controller")
public class ShowBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long bookId = Long.parseLong(request.getParameter("bid"));

        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");
        LeftShow leftShow = (LeftShow) getServletContext().getAttribute("left_show");
        leftShow.show(request, categoriesService, booksService);

        Book book = booksService.findBookById(bookId);
        request.setAttribute("book", book);

        UserService userService = (UserService) getServletContext().getAttribute("user_service");
        User sellersName = userService.findUserById(book.getSellerId());
        request.setAttribute("seller", sellersName);

        Cookie[] cookies = request.getCookies();

        int countProduct = 0;
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("BookAdded")) {
                countProduct++;
            }
        }

        request.setAttribute("countProduct", countProduct);

        getServletContext().getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
