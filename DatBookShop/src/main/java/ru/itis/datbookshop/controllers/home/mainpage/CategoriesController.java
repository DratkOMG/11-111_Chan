package ru.itis.datbookshop.controllers.home.mainpage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.product.CategoriesService;
import ru.itis.datbookshop.show.LeftShow;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesController", value = "/categories_controller")
public class CategoriesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long categoriesId = Long.parseLong(request.getParameter("cid"));
        request.setAttribute("tag", categoriesId);

        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");
        LeftShow leftShow = (LeftShow) getServletContext().getAttribute("left_show");
        leftShow.show(request, categoriesService, booksService);

        List<Book> bookList = booksService.findTop3ByCategoriesId(categoriesId);
        request.setAttribute("listBooks", bookList);

        Cookie[] cookies = request.getCookies();

        int countProduct = 0;
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("BookAdded")) {
                countProduct++;
            }
        }

        request.setAttribute("countProduct", countProduct);

        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long categoriesId = Long.parseLong(request.getParameter("cid"));
        request.setAttribute("tag", categoriesId);

        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        List<Book> bookList = booksService.findTop3ByCategoriesId(categoriesId);

        for (Book book : bookList) {
            response.getWriter().println(
                    "<div class=\"product col-12 col-md-6 col-lg-4\">\n" +
                            "                        <div class=\"card\">\n" +
                            "                            <img class=\"card-img-top\" src=\"" + book.getImage() + "\" alt=\"Book image\">\n" +
                            "                            <div class=\"card-body\">\n" +
                            "                                <h4 class=\"card-title show_txt\">\n" +
                            "                                    <a href=\"show_book_controller?bid=" + book.getId() + "\" title=\"View Product\">" + book.getTitle() + "</a>\n" +
                            "                                </h4>\n" +
                            "                                <p class=\"card-text show_txt\">" + book.getDescription() + "</p>\n" +
                            "                                <div class=\"row\">\n" +
                            "                                    <div class=\"col\">\n" +
                            "                                        <p class=\"btn btn-danger btn-block\">" + book.getPrice() + " $</p>\n" +
                            "                                    </div>\n" +
                            "                                    <div class=\"col\">\n" +
                            "                                        <a href=\"#\" class=\"btn btn-success btn-block\">Add to cart</a>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>"

            );
        }
    }
}
