package ru.itis.datbookshop.services.changer.impl;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datbookshop.repositories.product.BooksRepository;
import ru.itis.datbookshop.services.changer.ChangeBookService;
import ru.itis.datbookshop.services.check.CheckBookService;
import ru.itis.datbookshop.services.check.impl.CheckBookServiceImpl;
import ru.itis.datbookshop.services.product.impl.BooksServiceImpl;

public class ChangeBookServiceImpl extends BooksServiceImpl implements ChangeBookService {

    private final CheckBookService checkBookService = new CheckBookServiceImpl();

    public ChangeBookServiceImpl(BooksRepository booksRepository) {
        super(booksRepository);
    }

    @Override
    public void changeTitle(HttpServletRequest request) {
        String title = request.getParameter("title");
        long id = Long.parseLong(request.getParameter("bid"));

        if (!checkBookService.checkTitle(title)) {
            request.setAttribute("errorTitle", "Bad Title :((");
        } else if (!title.equals(booksRepository.getTitleById(id))) {
            booksRepository.updateTitleById(title, id);
            request.setAttribute("niceTitle", "Niceeee!");
        }
    }

    @Override
    public void changeImage(HttpServletRequest request) {
        String image = request.getParameter("image");
        long id = Long.parseLong(request.getParameter("bid"));

        if (!checkBookService.checkImage(image)) {
            request.setAttribute("errorImage", "We need image :((");
        } else if (!image.equals(booksRepository.getImageById(id))) {
            booksRepository.updateImageById(image, id);
            request.setAttribute("niceImage", "Wow, nice");
        }
    }

    @Override
    public void changePrice(HttpServletRequest request) {
        String priceStr = request.getParameter("price");
        long id = Long.parseLong(request.getParameter("bid"));

        if (checkBookService.checkPrice(priceStr)) {
            int price = Integer.parseInt(priceStr);
            if (!priceStr.equals(booksRepository.getPriceById(id))) {
                booksRepository.updatePriceById(price, id);
                request.setAttribute("nicePrice", "Done!");
            }
        } else {
            request.setAttribute("price", priceStr);
            request.setAttribute("errorPrice", "Your price is not correct");
        }
    }

    @Override
    public void changeAuthor(HttpServletRequest request) {
        String author = request.getParameter("author");
        long id = Long.parseLong(request.getParameter("bid"));

        if (!checkBookService.checkAuthor(author)) {
            request.setAttribute("errorAuthor", "Who is author, bro?");
        } else if (!author.equals(booksRepository.getAuthorById(id))) {
            booksRepository.updateAuthorById(author, id);
            request.setAttribute("niceAuthor", "Good bro");
        }
    }

    @Override
    public void changeDescription(HttpServletRequest request) {
        String description = request.getParameter("description");
        long id = Long.parseLong(request.getParameter("bid"));

        if (!checkBookService.checkDescription(description)) {
            request.setAttribute("errorDescription", "Description pleaseee!!");
        } else if (!description.equals(booksRepository.getDescriptionById(id)) && !description.isBlank()) {
            booksRepository.updateDescriptionById(description, id);
            request.setAttribute("niceDescription", "Changed!");
        }
    }

    @Override
    public void changeCategoryId(HttpServletRequest request) {
        String categoryId = request.getParameter("category");
        long id = Long.parseLong(request.getParameter("bid"));

        if (!categoryId.equals(booksRepository.getCategoryIdById(id))) {
            booksRepository.updateCategoryIdById(Long.parseLong(categoryId), id);
            request.setAttribute("niceCategory", "Doneee");
        }
    }
}
