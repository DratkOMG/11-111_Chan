package ru.itis.datbookshop.services.changer;

import jakarta.servlet.http.HttpServletRequest;

public interface ChangeBookService {
    void changeTitle(HttpServletRequest request);
    void changeImage(HttpServletRequest request);
    void changePrice(HttpServletRequest request);
    void changeAuthor(HttpServletRequest request);
    void changeDescription(HttpServletRequest request);
    void changeCategoryId(HttpServletRequest request);

}
