package ru.itis.datbookshop.repositories.product;

import ru.itis.datbookshop.models.Book;

import java.util.List;

public interface BooksRepository {
    Book getLastBook();

    Book getHottestBook();

    Book getBookById(long bookId);

    String getTitleById(long id);

    String getImageById(long id);

    String getPriceById(long id);

    String getAuthorById(long id);

    String getDescriptionById(long id);

    String getCategoryIdById(long id);

    void updateTitleById(String title, long id);

    void updateImageById(String image, long id);

    void updatePriceById(int price, long id);

    void updateAuthorById(String author, long id);

    void updateDescriptionById(String description, long id);

    void updateCategoryIdById(long categoryId, long id);

    void insertBook(Book book);

    void deleteBook(long id);

    List<Book> getAllBook();

    List<Book> getBooksByCategoriesId(long categoriesId);

    List<Book> getBooksByTitleLike(String title);

    List<Book> getTop3Book();

    List<Book> getTop12Book();

    List<Book> getNext3Book(int amount);

    List<Book> getNext10Book(int amount);

    List<Book> getNext10BookBySellerId(int amount, long id);

    List<Book> getTop3BookByCategoriesId(long categoriesId);

    List<Book> getNext3BookByCategoriesId(int amount, long categoriesId);

    List<Book> getTop10BookBySellerId(long id);

    List<Book> getBooksBySellerId(long eId);

    List<Book> getAllBookBySellerId(long sId);
}
