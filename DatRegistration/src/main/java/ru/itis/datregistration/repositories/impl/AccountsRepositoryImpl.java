package ru.itis.datregistration.repositories.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.datregistration.repositories.AccountsRepository;
import ru.itis.datregistration.repositories.Repository;
import ru.itis.datregistration.models.Account;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsRepositoryImpl extends Repository implements AccountsRepository{
    private final RowMapper<Account> accountRowMapper = ((rs, rowNum) -> Account.builder()
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .yearOfBirth(rs.getInt("year_of_birth"))
            .build());

    public Account signIn(String email, String password) {
        String SELECT_ACCOUNT = "select * from account where email = :email and password = :password";

        Map<String, Object> params = new HashMap<>();

        params.put("email", email);
        params.put("password", password);

        List<Account> account = namedParameterJdbcTemplate.query(SELECT_ACCOUNT, params, accountRowMapper);
        try {
            return account.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean findByEmail(String email) {
        String SQL_SELECT_EMAIL = "select * from account where email = :email";

        try {
            namedParameterJdbcTemplate.queryForObject(SQL_SELECT_EMAIL, Collections.singletonMap("email", email), accountRowMapper);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public void signUp(Account account) {
        String INSERT_INTO_ACCOUNT = "insert into account(email, password, year_of_birth) values (:email, :password, :year_of_birth);";

        Map<String, Object> params = new HashMap<>();
        params.put("email", account.getEmail());
        params.put("password", account.getPassword());
        params.put("year_of_birth", account.getYearOfBirth());

        namedParameterJdbcTemplate.update(INSERT_INTO_ACCOUNT, params);
    }

    public void changePassword(Account account) {
        String UPDATE_ACCOUNT = "update account set password = :pass where email = :email";

        Map<String, Object> params = new HashMap<>();
        params.put("pass", account.getPassword());
        params.put("email", account.getEmail());

        namedParameterJdbcTemplate.update(UPDATE_ACCOUNT, params);

    }

    public static void main(String[] args) {
        AccountsRepositoryImpl usersRepositoryImpl = new AccountsRepositoryImpl();
//        Account account1 = Account.builder()
//                .email("hiewewewfwfwehdi@gmail.com")
//                .password("123123213213213")
//                .yearOfBirth(2020)
//                .build();
//        usersRepositoryImpl.changePassword(account1);
//        System.out.println(usersRepositoryImpl.login("dat@email.com", "123asd"));
        System.out.println(usersRepositoryImpl.findByEmail("dat@email.com"));
    }
}
