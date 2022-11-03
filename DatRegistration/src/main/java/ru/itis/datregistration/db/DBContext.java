package ru.itis.datregistration.db;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static HikariDataSource instance;
//    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5432/register",
//                "postgres",
//                "2702"
//        );
//    }

    public HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/register");
        config.setUsername("postgres");
        config.setPassword("2702");
        config.setMaximumPoolSize(20);
        config.setDriverClassName("org.postgresql.Driver");

        if (instance == null) {
            instance = new HikariDataSource(config);
        }
        return instance;
    }

    public static void main(String[] args) {
//        try {
//            System.out.println(new DBContext().getConnection());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
