package ru.itis.datbookshop.services.check.impl;

import ru.itis.datbookshop.services.check.CheckBookService;

public class CheckBookServiceImpl implements CheckBookService {
    @Override
    public boolean checkTitle(String title) {
        return !(title.isEmpty() || title.isBlank());
    }

    @Override
    public boolean checkImage(String image) {
        return !(image.isBlank() || image.isEmpty());
    }

    @Override
    public boolean checkPrice(String priceStr) {
        try {
            int price = Integer.parseInt(priceStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean checkAuthor(String author) {
        return !(author.isEmpty() || author.isBlank());
    }

    @Override
    public boolean checkDescription(String description) {
        return !(description.isBlank() || description.isEmpty());
    }
}
