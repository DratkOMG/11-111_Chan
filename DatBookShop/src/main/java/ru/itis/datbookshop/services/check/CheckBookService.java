package ru.itis.datbookshop.services.check;

public interface CheckBookService {
    boolean checkTitle(String title);
    boolean checkImage(String image);
    boolean checkPrice(String price);
    boolean checkAuthor(String author);
    boolean checkDescription(String description);
}
