package ru.itis.datbookshop.controllers.home.mainpage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.repositories.product.BooksRepository;
import ru.itis.datbookshop.services.product.BooksService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadMoreController", value = "/load_more_controller")
//get use by manager
// post use by main page
public class LoadMoreController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int amount = Integer.parseInt(request.getParameter("exits"));
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");

        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("root_id");
        List<Book> bookList = booksService.findNext10BookBySellerId(amount, id);

        for (Book book : bookList) {
            response.getWriter().println("<tr class=\"product\">\n" +
                    "                    <td>" + book.getId() + "</td>\n" +
                    "                    <td>" + book.getTitle() + "</td>\n" +
                    "                    <td>\n" +
                    "                        <img src=\"" + book.getImage() + "\">\n" +
                    "                    </td>\n" +
                    "                    <td>" + book.getPrice() + " $</td>\n" +
                    "                    <td> " + book.getAuthor() + " </td>\n" +
                    "                    <td>" + book.getDescription() + " </td>\n" +
                    "                    <td>\n" +
                    "                        <a href=\"/DatBookShop_war_exploded/load_book_controller?bid=" + book.getId() + "\" class=\"edit\" data-toggle=\"modal\">\n" +
                    "                            <i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Edit\">&#xE254;</i>\n" +
                    "                        </a>\n" +
                    "                        <a href=\"/DatBookShop_war_exploded/delete_book_controller?bid=" + book.getId() + "\" class=\"delete\" data-toggle=\"modal\">\n" +
                    "                            <i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Delete\">&#xE872;</i>\n" +
                    "                        </a>\n" +
                    "                    </td>\n" +
                    "                </tr>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int amount = Integer.parseInt(request.getParameter("exits"));
        if (amount == 0) {
            return;
        }
        BooksService booksService = (BooksService) getServletContext().getAttribute("book_service");
        String categoriesIdStr = request.getParameter("cid");

        List<Book> bookList;

        if (categoriesIdStr.isEmpty()) {
            bookList = booksService.findNext3Book(amount);
        } else {
            long categoriesId = Long.parseLong(categoriesIdStr);
            bookList = booksService.findNext3ByCategoriesId(amount, categoriesId);
        }

        for (Book book : bookList) {
            response.getWriter().println(
                    "<div class=\"product col-12 col-md-6 col-lg-4\">\n" +
                            "                        <div class=\"card\">\n" +
                            "                            <img class=\"\" src=\"" + book.getImage() + "\" alt=\"Book image\">\n" +
                            "                            <div class=\"card-body\">\n" +
                            "                                <h4 class=\"card-title show_txt\">\n" +
                            "                                    <a href=\"show_book_controller?bid=" + book.getId() + "\" title=\"View Product\">" + book.getTitle() + "</a>\n" +
                            "                                </h4>\n" +
                            "                                <p class=\"card-text show_txt\">" + book.getDescription() + "</p>\n" +
                            "                                <div class=\"row\">\n" +
                            "                                    <div class=\"col\">\n" +
                            "                                        <a href=\"/DatBookShop_war_exploded/add_cart_controller?bid=" + book.getId() + "\" class=\"btn btn-danger btn-block\">" + book.getPrice() + " $</a>\n" +
                            "                                    </div>\n" +
                            "                                    <div class=\"col\">\n" +
                            "                                        <button onclick=\"addToCart(" + book.getId() + ")\" class=\"btn btn-success btn-block\">Add to cart</button>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>"

            );
        }


    }

}
