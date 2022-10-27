package ru.itis.datregistration.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.datregistration.db.DBContext;

public class Repository {
    private final DBContext dbContext = new DBContext();
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate =
            new NamedParameterJdbcTemplate(dbContext.getDataSource());

}
