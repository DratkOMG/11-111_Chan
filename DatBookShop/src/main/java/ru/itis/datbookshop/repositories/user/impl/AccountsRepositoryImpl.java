package ru.itis.datbookshop.repositories.user.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.datbookshop.models.Account;
import ru.itis.datbookshop.repositories.user.AccountsRepository;
import ru.itis.datbookshop.repositories.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsRepositoryImpl extends Repository implements AccountsRepository {
    private final RowMapper<Account> accountRowMapper = (rs, rowNum) -> Account.builder()
            .accountId(rs.getLong("account_id"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .isAdmin(rs.getInt("is_admin"))
            .isSeller(rs.getInt("is_seller"))
            .build();

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
    public String getPassword(String email) {
        String SELECT_PASS = "select * from account where email = :email";

        List<Account> account = namedParameterJdbcTemplate.query(SELECT_PASS,
                Collections.singletonMap("email", email),
                accountRowMapper);

        return account.get(0).getPassword();
    }

    public Account getAccountByEmail(String email) {
        String SELECT_ACCOUNT_BY_EMAIL = "select * from account where email = :email";

        List<Account> account = namedParameterJdbcTemplate.query(SELECT_ACCOUNT_BY_EMAIL,
                Collections.singletonMap("email", email),
                accountRowMapper);

        try {
            return account.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Account getAccountById(long id) {
        String SELECT_ACC_BY_ID = "select * from account where account_id = :id";

        List<Account> account = namedParameterJdbcTemplate.query(SELECT_ACC_BY_ID,
                Collections.singletonMap("id", id),
                accountRowMapper);

        return account.get(0);
    }

    @Override
    public List<Account> getNext10Account(int amount) {
        String SELECT_NEXT_10_ACCOUNT = "select * from account\n" +
                "order by account_id\n" +
                "offset :amount rows\n" +
                "fetch next 10 rows only";

        return namedParameterJdbcTemplate.query(SELECT_NEXT_10_ACCOUNT,
                Collections.singletonMap("amount", amount),
                accountRowMapper);
    }

    @Override
    public void updateSeller(long eId, String seller) {
        String UPDATE_SELLER_BY_ID = "update account\n" +
                "set is_seller = '" + seller + "'" +
                "where account_id = :id";


        namedParameterJdbcTemplate.update(UPDATE_SELLER_BY_ID, Collections.singletonMap("id", eId));
    }

    @Override
    public void deleteAccById(long eId) {
        String DELETE_ACC_BY_ID = "delete from account\n" +
                "where account_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", eId);

        namedParameterJdbcTemplate.update(DELETE_ACC_BY_ID, params);
    }


    public void signUp(Account account) {
        String INSERT_INTO_ACCOUNT = "insert into account(email, password) values (:email, :password);";

        Map<String, Object> params = new HashMap<>();
        params.put("email", account.getEmail());
        params.put("password", account.getPassword());

        namedParameterJdbcTemplate.update(INSERT_INTO_ACCOUNT, params);
    }

    @Override
    public List<Account> getAll() {
        String SELECT_ALL = "select * from account order by account_id";
        return namedParameterJdbcTemplate.query(SELECT_ALL, accountRowMapper);
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
//        System.out.println(usersRepositoryImpl.getByEmail("dat@email.com"));
    }
}
