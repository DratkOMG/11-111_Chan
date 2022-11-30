package ru.itis.datbookshop.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.datbookshop.dbcontext.DBContext;

public class Repository {
    private final DBContext dbContext = new DBContext();

    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate =
            new NamedParameterJdbcTemplate((dbContext.getDataSource()));
}
