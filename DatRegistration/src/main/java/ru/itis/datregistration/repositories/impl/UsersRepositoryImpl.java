package ru.itis.datregistration.repositories.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.datregistration.repositories.Repository;
import ru.itis.datregistration.repositories.UsersRepository;
import ru.itis.datregistration.models.Account;
import ru.itis.datregistration.models.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UsersRepositoryImpl extends Repository implements UsersRepository {
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> User.builder()
            .id(rs.getLong("id"))
            .email(rs.getString("email"))
            .userName(rs.getString("user_name"))
            .age(rs.getInt("age"))
            .phoneNumber(rs.getLong("phone_number"))
            .build();

    @Override
    public void createProfile(Account account) {
        User user = User.builder()
                .email(account.getEmail())
                .age(2022 - account.getYearOfBirth())
                .build();

        Map<String, Object> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("age", 2022 - account.getYearOfBirth());
        params.put("user_name", "username");

        SimpleJdbcInsert insert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate());

        long id = insert.withTableName("user_profile")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(params)).longValue();

        user.setId(id);
        user.setUserName("username" + id);
        user.setPhoneNumber(null);

        String UPDATE_USER_NAME = "update user_profile\n" +
                "set user_name = :user_name\n" +
                "where user_name = 'username';";


        namedParameterJdbcTemplate.update(UPDATE_USER_NAME, Collections.singletonMap("user_name", user.getUserName()));

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
        String SELECT_USER_NAME = "select * from user_profile where user_name = :username";

        try {
            return namedParameterJdbcTemplate.queryForObject(SELECT_USER_NAME,
                    Collections.singletonMap("username", username),
                    userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getProfileByNumberPhone(long numberPhone) {
        String SELECT_USER_NAME = "select * from user_profile where phone_number = :numberPhone";
        try {
            return namedParameterJdbcTemplate.queryForObject(SELECT_USER_NAME,
                    Collections.singletonMap("numberPhone", numberPhone),
                    userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public void updateUsername(String username, String email) {
        String UPDATE_USERNAME = "update user_profile\n" +
                "set user_name = :username\n" +
                "where email = :email;";
        Map<String, Object> params = new HashMap<>();

        params.put("username", username);
        params.put("email", email);

        namedParameterJdbcTemplate.update(UPDATE_USERNAME, params);
    }


    @Override
    public void updateNumberPhone(long numberPhone, String email) {
        String UPDATE_NUMBER_PHONE = "update user_profile\n" +
                "set phone_number = :number_phone\n" +
                "where email = :email;";

        Map<String, Object> params = new HashMap<>();

        params.put("number_phone", numberPhone);
        params.put("email", email);

        namedParameterJdbcTemplate.update(UPDATE_NUMBER_PHONE, params);
    }


    public static void main(String[] args) {
//        Account account = Account.builder()
//                .email("dat09@gmail.com")
//                .password("123123123")
//                .build();
//        UsersRepository usersRepository = new UsersRepositoryImpl();
//        usersRepository.createProfile(account);
//        System.out.println(usersRepository.getUsername("testr1"));
    }
}
