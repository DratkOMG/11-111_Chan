package ru.itis.datbookshop.show;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.models.Categories;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.product.CategoriesService;

import java.util.List;

public class LeftShow {
    public LeftShow() {}

    public void show(HttpServletRequest request, CategoriesService categoriesService, BooksService booksService) {
        List<Categories> categoriesList = categoriesService.findAll();
        request.setAttribute("listCategories", categoriesList);

        Book lastBook = booksService.findLastBook();
        request.setAttribute("lastBook", lastBook);

        Book hotBook = booksService.findHottestBook();
        request.setAttribute("hotBook", hotBook);
    }


}
