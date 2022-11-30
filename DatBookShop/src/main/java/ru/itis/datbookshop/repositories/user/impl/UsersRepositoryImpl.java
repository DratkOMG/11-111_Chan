package ru.itis.datbookshop.repositories.user.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.datbookshop.models.Account;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.repositories.Repository;
import ru.itis.datbookshop.repositories.user.UsersRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersRepositoryImpl extends Repository implements UsersRepository {

    //language=SQL
    private final String SELECT_USER_BY_EMAIL = "select * from user_profile where email = :email";

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> User.builder()
            .userId(rs.getLong("user_id"))
            .email(rs.getString("email"))
            .userName(rs.getString("user_name"))
            .age(rs.getInt("age"))
            .balance(rs.getInt("balance"))
            .sex(rs.getString("sex"))
            .phoneNumber(rs.getLong("phone_number"))
            .city(rs.getString("city"))
            .build();

    @Override
    public User getUserByEmail(String email) {
        List<User> users = namedParameterJdbcTemplate.query(SELECT_USER_BY_EMAIL,
                Collections.singletonMap("email", email),
                userRowMapper);

        return users.get(0);
    }

    @Override
    public void createProfile(Account account, String userName) {
        String INSERT_INTO_USER_PROFILE = "insert into user_profile(email, user_name)\n" +
                "values (:email, :user_name)";

        User user = User.builder()
                .email(account.getEmail())
                .userName(userName)
                .build();

        Map<String, Object> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("user_name", userName);


        namedParameterJdbcTemplate.update(INSERT_INTO_USER_PROFILE, params);

    }

    @Override
    public List<User> getAll() {
        String SELECT_ALL = "select * from user_profile order by user_id";
        return namedParameterJdbcTemplate.query(SELECT_ALL, userRowMapper);
    }

    @Override
    public User getProfileById(long id) {
        String SELECT_PROFILE_BY_ID = "select * from user_profile where user_id = :id";

        List<User> user = namedParameterJdbcTemplate.query(SELECT_PROFILE_BY_ID,
                Collections.singletonMap("id", id),
                userRowMapper);

        return user.get(0);
    }

    @Override
    public User getProfileByEmail(String email) {
        String SELECT_PROFILE = "select * from user_profile where email = :email";
        try {
            return namedParameterJdbcTemplate.queryForObject(SELECT_PROFILE,
                    Collections.singletonMap("email", email),
                    userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public User getProfileByUsername(String username) {
        String SELECT_USER = "select * from user_profile where user_name = :username";

        try {
            return namedParameterJdbcTemplate.queryForObject(SELECT_USER,
                    Collections.singletonMap("username", username),
                    userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getProfileByNumberPhone(long numberPhone) {
        String SELECT_USER = "select * from user_profile where phone_number = :numberPhone";
        try {
            return namedParameterJdbcTemplate.queryForObject(SELECT_USER,
                    Collections.singletonMap("numberPhone", numberPhone),
                    userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getUsernameByEmail(String email) {
        User user = getUserByEmail(email);

        return user.getUserName();
    }

    @Override
    public String getNumberPhoneByEmail(String email) {
        User user = getUserByEmail(email);

        return user.getPhoneNumber().toString();
    }

    @Override
    public String getCityByEmail(String email) {
        User user = getUserByEmail(email);

        return user.getCity();
    }

    @Override
    public String getSexByEmail(String email) {
        User user = getUserByEmail(email);

        return user.getSex();
    }

    @Override
    public String getAgeByEmail(String email) {
        User user = getUserByEmail(email);

        return user.getAge().toString();
    }

    @Override
    public void updateUsernameByEmail(String username, String email) {
        String UPDATE_USERNAME = "update user_profile\n" +
                "set user_name = :username\n" +
                "where email = :email;";
        Map<String, Object> params = new HashMap<>();

        params.put("username", username);
        params.put("email", email);

        namedParameterJdbcTemplate.update(UPDATE_USERNAME, params);
    }


    @Override
    public void updateNumberPhoneByEmail(long numberPhone, String email) {
        String UPDATE_NUMBER_PHONE = "update user_profile\n" +
                "set phone_number = :number_phone\n" +
                "where email = :email;";

        Map<String, Object> params = new HashMap<>();

        params.put("number_phone", numberPhone);
        params.put("email", email);

        namedParameterJdbcTemplate.update(UPDATE_NUMBER_PHONE, params);
    }

    @Override
    public void updateCityByEmail(String city, String email) {
        String UPDATE_CITY_BY_EMAIL = "update user_profile\n" +
                "set city = :city\n" +
                "where email = :email";

        Map<String, Object> params = new HashMap<>();

        params.put("city", city);
        params.put("email", email);

        namedParameterJdbcTemplate.update(UPDATE_CITY_BY_EMAIL, params);
    }

    @Override
    public void updateSexByEmail(String sex, String email) {
        String UPDATE_SEX_BY_EMAIL = "update user_profile\n" +
                "set sex = :sex\n" +
                "where email = :email";

        Map<String, Object> params = new HashMap<>();

        params.put("sex", sex);
        params.put("email", email);

        namedParameterJdbcTemplate.update(UPDATE_SEX_BY_EMAIL, params);
    }

    @Override
    public void updateAgeByEmail(int age, String email) {
        String UPDATE_AGE_BY_EMAIL = "update user_profile\n" +
                "set age = :age\n" +
                "where email = :email";

        Map<String, Object> params = new HashMap<>();

        params.put("age", age);
        params.put("email", email);

        namedParameterJdbcTemplate.update(UPDATE_AGE_BY_EMAIL, params);
    }

    @Override
    public List<User> getNext10User(int amount) {
        String SELECT_NEXT_10_USER = "select * from user_profile\n" +
                "order by user_id\n" +
                "offset :amount rows\n" +
                "fetch next 10 rows only";

        return namedParameterJdbcTemplate.query(SELECT_NEXT_10_USER,
                Collections.singletonMap("amount", amount),
                userRowMapper);
    }

    @Override
    public void updateBalanceById(int money, long id) {
        String UPDATE_BALANCE_BY_ID = "update user_profile\n" +
                "set balance = balance + :money\n" +
                "where user_id = :id";

        Map<String, Object> params = new HashMap<>();

        params.put("money", money);
        params.put("id", id);

        namedParameterJdbcTemplate.update(UPDATE_BALANCE_BY_ID, params);
    }


    public static void main(String[] args) {
        Account account = Account.builder()
                .email("dat09@gmail.com")
                .password("123123123")
                .build();
        UsersRepository usersRepository = new UsersRepositoryImpl();
        System.out.println(usersRepository.getCityByEmail("dratkomg@gmail.com"));
        usersRepository.getProfileByEmail("dratkomg@gmail.com");
    }
}
