package ru.itis.datbookshop.services.product;

import ru.itis.datbookshop.models.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll();
    void addCategory(String categoriesName);
}
