package ru.itis.datbookshop.repositories.product.impl;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.repositories.Repository;
import ru.itis.datbookshop.repositories.product.BooksRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksRepositoryImpl extends Repository implements BooksRepository {
    //language=SQL
    private final String SELECT_BOOK_BY_ID = "select * from book where book_id = :id";
    private final RowMapper<Book> bookRowMapper = (rs, rowNum) ->
            Book.builder()
                    .id(rs.getLong("book_id"))
                    .title(rs.getString("title"))
                    .image(rs.getString("image"))
                    .price(rs.getInt("price"))
                    .author(rs.getString("author"))
                    .quantitySold(rs.getInt("quantity_sold"))
                    .description(rs.getString("description"))
                    .categoryId(rs.getLong("categories_id"))
                    .sellerId(rs.getLong("seller_id"))
                    .build();


    @Override
    public List<Book> getAllBook() {
        String SELECT_ALL = "select * from book order by book_id";
        return namedParameterJdbcTemplate.query(SELECT_ALL, bookRowMapper);
    }

    @Override
    public Book getLastBook() {
        String SELECT_LAST_BOOK = "select * from book\n" +
                "order by book_id desc\n" +
                "limit 1";

        List<Book> bookList = namedParameterJdbcTemplate.query(SELECT_LAST_BOOK, bookRowMapper);
        return bookList.get(0);
    }

    @Override
    public Book getHottestBook() {
        String SELECT_HOTTEST_BOOK = "select * from book\n" +
                "order by quantity_sold desc\n" +
                "limit 1";

        List<Book> bookList = namedParameterJdbcTemplate.query(SELECT_HOTTEST_BOOK, bookRowMapper);
        return bookList.get(0);
    }

    @Override
    public List<Book> getBooksByCategoriesId(long categoriesId) {
        String SELECT_BOOK_BY_CATEGORIES_ID = "select *\n" +
                "from book\n" +
                "where categories_id = :categoriesId";

        return namedParameterJdbcTemplate.query(SELECT_BOOK_BY_CATEGORIES_ID,
                Collections.singletonMap("categoriesId", categoriesId),
                bookRowMapper);
    }

    @Override
    public Book getBookById(long bookId) {
        String SELECT_BOOK_BY_ID = "select * from book\n" +
                "where book_id = :bookId";

        List<Book> bookList = namedParameterJdbcTemplate.query(SELECT_BOOK_BY_ID,
                Collections.singletonMap("bookId", bookId),
                bookRowMapper);

        return bookList.get(0);
    }

    @Override
    public String getTitleById(long id) {
        Book book = getBookById(id);

        return book.getTitle();
    }

    @Override
    public String getImageById(long id) {
        Book book = getBookById(id);

        return book.getImage();
    }

    @Override
    public String getPriceById(long id) {
        Book book = getBookById(id);

        return book.getPrice().toString();
    }

    @Override
    public String getAuthorById(long id) {
        Book book = getBookById(id);

        return book.getAuthor();
    }

    @Override
    public String getDescriptionById(long id) {
        Book book = getBookById(id);

        return book.getDescription();
    }

    @Override
    public String getCategoryIdById(long id) {
        Book book = getBookById(id);

        return book.getCategoryId().toString();
    }

    @Override
    public List<Book> getBooksByTitleLike(String title) {
        String SELECT_BOOK_BY_LIKE_TITLE = "select * from book\n" +
                "where title ilike :title";

        return namedParameterJdbcTemplate.query(SELECT_BOOK_BY_LIKE_TITLE,
                Collections.singletonMap("title", "%" + title + "%"),
                bookRowMapper);
    }

    @Override
    public void updateTitleById(String title, long id) {
        String UPDATE_TITLE_BY_ID = "update book\n" +
                "set title = :title\n" +
                "where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("id", id);

        namedParameterJdbcTemplate.update(UPDATE_TITLE_BY_ID, params);
    }

    @Override
    public void updateImageById(String image, long id) {
        String UPDATE_IMAGE_BY_ID = "update book\n" +
                "set image = :image\n" +
                "where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("image", image);
        params.put("id", id);

        namedParameterJdbcTemplate.update(UPDATE_IMAGE_BY_ID, params);
    }

    @Override
    public void updatePriceById(int price, long id) {
        String UPDATE_PRICE_BY_ID = "update book\n" +
                "set price = :price\n" +
                "where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("price", price);
        params.put("id", id);

        namedParameterJdbcTemplate.update(UPDATE_PRICE_BY_ID, params);
    }

    @Override
    public void updateAuthorById(String author, long id) {
        String UPDATE_AUTHOR_BY_ID = "update book\n" +
                "set author = :author\n" +
                "where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("author", author);
        params.put("id", id);

        namedParameterJdbcTemplate.update(UPDATE_AUTHOR_BY_ID, params);
    }

    @Override
    public void updateDescriptionById(String description, long id) {
        String UPDATE_DESCRIPTION_BY_ID = "update book\n" +
                "set description = :description\n" +
                "where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("description", description);
        params.put("id", id);

        namedParameterJdbcTemplate.update(UPDATE_DESCRIPTION_BY_ID, params);
    }

    @Override
    public void updateCategoryIdById(long categoryId, long id) {
        String UPDATE_CATEGORY_BY_ID = "update book\n" +
                "set categories_id = :category_id\n" +
                "where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("category_id", categoryId);
        params.put("id", id);

        namedParameterJdbcTemplate.update(UPDATE_CATEGORY_BY_ID, params);
    }

    @Override
    public void insertBook(Book book) {
        String INSERT_INTO_BOOK = "insert into book(title, image, price, author, description, categories_id, seller_id)\n" +
                "values (:title, :image,\n" +
                ":price, :author, :description, :category_id, :seller_id)";

        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("image", book.getImage());
        params.put("price", book.getPrice());
        params.put("author", book.getAuthor());
        params.put("description", book.getDescription());
        params.put("category_id", book.getCategoryId());
        params.put("seller_id", book.getSellerId());

        namedParameterJdbcTemplate.update(INSERT_INTO_BOOK, params);

    }

    @Override
    public void deleteBook(long id) {
        String DELETE_BOOK_BY_ID = "delete from book\n" +
                "where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        namedParameterJdbcTemplate.update(DELETE_BOOK_BY_ID, params);
    }

    @Override
    public List<Book> getTop3Book() {
        String SELECT_TOP_3 = "select * from book\n" +
                "order by book_id\n" +
                "limit 3";

        return namedParameterJdbcTemplate.query(SELECT_TOP_3, bookRowMapper);
    }

    @Override
    public List<Book> getTop12Book() {
        String SELECT_TOP_12 = "select * from book\n" +
                "order by book_id\n" +
                "limit 12";

        return namedParameterJdbcTemplate.query(SELECT_TOP_12, bookRowMapper);
    }

    @Override
    public List<Book> getNext3Book(int amount) {
        String SELECT_NEXT_3_BOOK = "select * from book\n" +
                "order by book_id\n" +
                "offset :amount rows\n" +
                "fetch next 3 rows only";

        return namedParameterJdbcTemplate.query(SELECT_NEXT_3_BOOK,
                Collections.singletonMap("amount", amount),
                bookRowMapper);
    }

    @Override
    public List<Book> getNext10Book(int amount) {
        String SELECT_NEXT_10_BOOK = "select * from book\n" +
                "order by book_id\n" +
                "offset :amount rows\n" +
                "fetch next 10 rows only";

        return namedParameterJdbcTemplate.query(SELECT_NEXT_10_BOOK,
                Collections.singletonMap("amount", amount),
                bookRowMapper);
    }

    @Override
    public List<Book> getNext10BookBySellerId(int amount, long id) {
        String SELECT_NEXT_10_BOOK_BY_SELLER_ID = "select * from book\n" +
                "where seller_id = :id\n" +
                "order by book_id\n" +
                "offset :amount rows\n" +
                "fetch next 10 rows only";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("amount", amount);

        return namedParameterJdbcTemplate.query(SELECT_NEXT_10_BOOK_BY_SELLER_ID,
                params,
                bookRowMapper);
    }

    @Override
    public List<Book> getTop3BookByCategoriesId(long categoriesId) {
        String SELECT_TOP_3_BY_CATEGORIES_ID = "select *\n" +
                "from book\n" +
                "where categories_id = :categoriesId\n" +
                "order by book_id\n" +
                "limit 3";

        return namedParameterJdbcTemplate.query(SELECT_TOP_3_BY_CATEGORIES_ID,
                Collections.singletonMap("categoriesId", categoriesId),
                bookRowMapper);
    }

    @Override
    public List<Book> getNext3BookByCategoriesId(int amount, long categoriesId) {
        String SELECT_NEXT_3_BOOK_BY_CATEGORIES_ID = "select * from book\n" +
                "where categories_id = :categoriesId\n" +
                "order by book_id\n" +
                "offset :amount rows\n" +
                "fetch next 3 rows only";

        Map<String, Object> params = new HashMap<>();
        params.put("categoriesId", categoriesId);
        params.put("amount", amount);

        return namedParameterJdbcTemplate.query(SELECT_NEXT_3_BOOK_BY_CATEGORIES_ID,
                params,
                bookRowMapper);
    }

    @Override
    public List<Book> getTop10BookBySellerId(long id) {
        String SELECT_TOP_10 = "select * from book\n" +
                "where seller_id = :id\n" +
                "order by book_id\n" +
                "limit 10";

        return namedParameterJdbcTemplate.query(SELECT_TOP_10,
                Collections.singletonMap("id", id),
                bookRowMapper);
    }

    @Override
    public List<Book> getBooksBySellerId(long eId) {
        String SELECT_BOOK_BY_SELLER_ID = "select *\n" +
                "from book\n" +
                "where seller_id = :eid " +
                "order by book_id";

        return namedParameterJdbcTemplate.query(SELECT_BOOK_BY_SELLER_ID,
                Collections.singletonMap("eid", eId),
                bookRowMapper);
    }

    @Override
    public List<Book> getAllBookBySellerId(long sId) {
        String SELECT_BOOKS_BY_SELLER_NAME = "select *\n" +
                "from book\n" +
                "where seller_id = :id";

        return namedParameterJdbcTemplate.query(SELECT_BOOKS_BY_SELLER_NAME,
                Collections.singletonMap("id", sId),
                bookRowMapper);
    }

    public static void main(String[] args) {
//        BooksRepository booksRepository = new BooksRepositoryImpl();
//        System.out.println(booksRepository.getBooksByTitleLike("The"));


    }


}
