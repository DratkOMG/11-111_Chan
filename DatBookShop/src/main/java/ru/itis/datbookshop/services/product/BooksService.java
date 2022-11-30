package ru.itis.datbookshop.services.product;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datbookshop.models.Book;

import java.util.List;

public interface BooksService {
    List<Book> findAll();
    List<Book> findAllBySellerId(long eId);
    List<Book> findAllByCategoriesId(long categoriesId);
    List<Book> findTop3ByCategoriesId(long categoriesId);
    List<Book> findNext3ByCategoriesId(int amount, long categoriesId);
    List<Book> findByLikeTitle(String title);
    List<Book> findTop3();
    List<Book> findTop12();
    List<Book> findTop10BySellerId(long id);
    List<Book> findNext3Book(int amount);
    List<Book> findNext10Book(int amount);
    List<Book> findNext10BookBySellerId(int amount, long id);
    Book findLastBook();
    Book findHottestBook();
    Book findBookById(long bookId);

    void updateBook(HttpServletRequest request);

    void addBook(HttpServletRequest request);

    void deleteBook(HttpServletRequest request);

    List<Book> findAllBookBySellerId(long sId);
}
