package ru.itis.datbookshop.dbcontext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.datbookshop.repositories.Repository;

public class DBContext {
    private static HikariDataSource instance;
    public HikariDataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/bookdatshop");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("2702");
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        if (instance == null) {
            instance = new HikariDataSource(hikariConfig);
        }
        return instance;
    }
}
