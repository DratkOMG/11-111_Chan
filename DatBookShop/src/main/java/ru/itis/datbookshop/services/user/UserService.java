package ru.itis.datbookshop.services.user;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datbookshop.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> findNext10User(int amount);
    User findUserById(long id);
    User findByEmail(String email);
    User findByUsername(String username);
    User findByNumberPhone(long numberPhone);
    void changeProfile(HttpServletRequest request);
    void donate(int money, long id);
}
