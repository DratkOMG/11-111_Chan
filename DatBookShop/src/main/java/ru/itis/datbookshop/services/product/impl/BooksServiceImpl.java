package ru.itis.datbookshop.services.product.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ru.itis.datbookshop.models.Book;
import ru.itis.datbookshop.repositories.product.BooksRepository;
import ru.itis.datbookshop.repositories.product.impl.BooksRepositoryImpl;
import ru.itis.datbookshop.services.changer.ChangeBookService;
import ru.itis.datbookshop.services.changer.impl.ChangeBookServiceImpl;
import ru.itis.datbookshop.services.check.CheckBookService;
import ru.itis.datbookshop.services.check.impl.CheckBookServiceImpl;
import ru.itis.datbookshop.services.product.BooksService;

import java.util.List;

public class BooksServiceImpl implements BooksService {
    protected BooksRepository booksRepository;

    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    @Override
    public List<Book> findAll() {
        return booksRepository.getAllBook();
    }

    @Override
    public List<Book> findAllBySellerId(long eId) {
        return booksRepository.getBooksBySellerId(eId);
    }

    @Override
    public List<Book> findAllByCategoriesId(long categoriesId) {
        return booksRepository.getBooksByCategoriesId(categoriesId);
    }

    @Override
    public List<Book> findTop3ByCategoriesId(long categoriesId) {
        return booksRepository.getTop3BookByCategoriesId(categoriesId);
    }

    @Override
    public List<Book> findNext3ByCategoriesId(int amount, long categoriesId) {
        return booksRepository.getNext3BookByCategoriesId(amount, categoriesId);
    }

    @Override
    public List<Book> findByLikeTitle(String title) {
        return booksRepository.getBooksByTitleLike(title);
    }

    @Override
    public List<Book> findNext3Book(int amount) {
        return booksRepository.getNext3Book(amount);
    }

    @Override
    public List<Book> findNext10Book(int amount) {
        return booksRepository.getNext10Book(amount);
    }

    @Override
    public List<Book> findNext10BookBySellerId(int amount, long id) {
        return booksRepository.getNext10BookBySellerId(amount, id);
    }

    @Override
    public List<Book> findTop3() {
        return booksRepository.getTop3Book();
    }

    @Override
    public List<Book> findTop12() {
        return booksRepository.getTop12Book();
    }

    @Override
    public List<Book> findTop10BySellerId(long id) {
        return booksRepository.getTop10BookBySellerId(id);
    }

    @Override
    public Book findLastBook() {
        return booksRepository.getLastBook();
    }

    @Override
    public Book findHottestBook() {
        return booksRepository.getHottestBook();
    }

    @Override
    public Book findBookById(long bookId) {
        return booksRepository.getBookById(bookId);
    }

    @Override
    public void updateBook(HttpServletRequest request) {
        ChangeBookService changeBookService = new ChangeBookServiceImpl(booksRepository);

        changeBookService.changeTitle(request);
        changeBookService.changeImage(request);
        changeBookService.changePrice(request);
        changeBookService.changeAuthor(request);
        changeBookService.changeDescription(request);
        changeBookService.changeCategoryId(request);

    }

    @Override
    public void addBook(HttpServletRequest request) {
        CheckBookService checkBookService = new CheckBookServiceImpl();
        boolean f = true;

        String title = request.getParameter("title");
        String image = request.getParameter("image");
        Integer price = null;
        String author = request.getParameter("author") ;
        String description = request.getParameter("description");
        Long category = Long.valueOf(request.getParameter("category"));
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("root_id");

        request.setAttribute("title", title);
        request.setAttribute("image", image);
        request.setAttribute("price", price);
        request.setAttribute("author", author);
        request.setAttribute("description", description);
        request.setAttribute("categoriesId", category);

        if (!checkBookService.checkTitle(title)) {
            request.setAttribute("errorTitle", "Title where bro?");
            f = false;
        }
        if (!checkBookService.checkImage(image)) {
            request.setAttribute("errorImage", "We need image");
            f = false;
        }
        if (!checkBookService.checkPrice(request.getParameter("price"))) {
            request.setAttribute("price", price);
            request.setAttribute("errorPrice", "??");
            f = false;
        } else {
            price = Integer.valueOf(request.getParameter("price"));
        }
        if (!checkBookService.checkAuthor(author)) {
            request.setAttribute("errorAuthor", "Who is author, bro?");
            f = false;
        }
        if (!checkBookService.checkDescription(description)) {
            request.setAttribute("errorDescription", "Hmm, is bad book?");
            f = false;
        }

        if (f) {
            Book book = Book.builder()
                    .title(title)
                    .image(image)
                    .price(price)
                    .author(author)
                    .description(description)
                    .categoryId(category)
                    .sellerId(id)
                    .build();
            booksRepository.insertBook(book);
            request.setAttribute("Done", "Added!");
        }
    }

    @Override
    public void deleteBook(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("bid"));
        booksRepository.deleteBook(id);
    }

    @Override
    public List<Book> findAllBookBySellerId(long sId) {
        return booksRepository.getAllBookBySellerId(sId);
    }

    public static void main(String[] args) {
        BooksService booksService = new BooksServiceImpl(new BooksRepositoryImpl());
        System.out.println(booksService.findByLikeTitle("father"));
    }
}
