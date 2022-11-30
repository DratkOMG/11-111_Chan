package ru.itis.datbookshop.repositories.user;

import ru.itis.datbookshop.models.Account;
import ru.itis.datbookshop.models.User;

import java.util.List;

public interface UsersRepository {
    User getUserByEmail(String email);
    void createProfile(Account account, String userName);
    List<User> getAll();
    User getProfileById(long id);
    User getProfileByEmail(String email);
    User getProfileByUsername(String username);
    User getProfileByNumberPhone(long numberPhone);
    String getUsernameByEmail(String email);
    String getNumberPhoneByEmail(String email);
    String getCityByEmail(String email);
    String getSexByEmail(String email);
    String getAgeByEmail(String email);
    void updateUsernameByEmail(String username, String email);
    void updateNumberPhoneByEmail(long numberPhone, String email);
    void updateCityByEmail(String city, String email);
    void updateSexByEmail(String sex, String email);
    void updateAgeByEmail(int age, String email);

    List<User> getNext10User(int amount);

    void updateBalanceById(int money, long id);
}
