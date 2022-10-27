package ru.itis.datregistration.services;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datregistration.models.User;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);
    User findByNumberPhone(long numberPhone);
    void changeUsername(HttpServletRequest request);
    void changeNumberPhone(HttpServletRequest request);
}
