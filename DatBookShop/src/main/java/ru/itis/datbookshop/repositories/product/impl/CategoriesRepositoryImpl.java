package ru.itis.datbookshop.repositories.product.impl;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.datbookshop.models.Categories;
import ru.itis.datbookshop.repositories.product.CategoriesRepository;
import ru.itis.datbookshop.repositories.Repository;

import java.util.Collections;
import java.util.List;

public class CategoriesRepositoryImpl extends Repository implements CategoriesRepository {
    private final RowMapper<Categories> categoriesRowMapper = (rs, rowNum) -> Categories.builder()
            .categoriesId(rs.getLong("categories_id"))
            .categoriesName(rs.getString("categories_name"))
            .build();

    @Override
    public List<Categories> getAllCategories() {
        String SELECT_ALL_CATEGORIES = "select * from categories";

        return namedParameterJdbcTemplate.query(SELECT_ALL_CATEGORIES, categoriesRowMapper);
    }

    @Override
    public void addCategory(String categoriesName) {
        String INSERT_INTO_CATEGORIES = "insert into categories(categories_name)\n" +
                "values (:category)";

        namedParameterJdbcTemplate.update(INSERT_INTO_CATEGORIES,
                Collections.singletonMap("category", categoriesName));
    }
}
