package ru.itis.datbookshop.services.product.impl;

import ru.itis.datbookshop.models.Categories;
import ru.itis.datbookshop.repositories.product.CategoriesRepository;
import ru.itis.datbookshop.services.product.CategoriesService;

import java.util.List;

public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesService) {
        this.categoriesRepository = categoriesService;
    }

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.getAllCategories();
    }

    @Override
    public void addCategory(String categoriesName) {
        categoriesRepository.addCategory(categoriesName);
    }
}
