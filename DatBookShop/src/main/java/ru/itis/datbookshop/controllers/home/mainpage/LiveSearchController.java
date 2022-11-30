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

@WebServlet(name = "LiveSearchController", value = "/live_search_controller")
public class LiveSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");

        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        CategoriesService categoriesService = (CategoriesService) getServletContext().getAttribute("categories_service");
        LeftShow leftShow = (LeftShow) getServletContext().getAttribute("left_show");
        leftShow.show(request, categoriesService, booksService);

        List<Book> bookList;

        if (search.isEmpty()) {
            bookList = booksService.findAll();
        } else {
            bookList = booksService.findByLikeTitle(search);
        }

        request.setAttribute("listBooks", bookList);

        for (Book book : bookList) {
            response.getWriter().println("<div class=\"col-12 col-md-6 col-lg-4\">\n" +
                    "                        <div class=\"card\">\n" +
                    "                            <img class=\"\" src=\"" + book.getImage() + "\" alt=\"Book image\">\n" +
                    "                            <div class=\"card-body\">\n" +
                    "                                <h4 class=\"card-title show_txt\">\n" +
                    "                                    <a href=\"show_book_controller?bid=" + book.getId() + "\" title=\"View Product\">" + book.getTitle() + "</a>\n" +
                    "                                </h4>\n" +
                    "                                <p class=\"card-text show_txt\">" + book.getDescription() + "</p>\n" +
                    "                                <div class=\"row\">\n" +
                    "                                    <div class=\"col\">\n" +
                    "                                         <a href=\"/DatBookShop_war_exploded/add_cart_controller?bid=" + book.getId() + "\" class=\"btn btn-danger btn-block\">" + book.getPrice() + " $</a>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"col\">\n" +
                    "                                          <button onclick=\"addToCart(" + book.getId() + ")\" class=\"btn btn-success btn-block\">Add to cart</button>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>");

        }
    }
}
