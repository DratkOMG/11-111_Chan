package ru.itis.datbookshop.repositories.product;

import ru.itis.datbookshop.models.Categories;

import java.util.List;

public interface CategoriesRepository {
    List<Categories> getAllCategories();

    void addCategory(String categoriesName);
}
