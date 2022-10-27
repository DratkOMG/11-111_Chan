package ru.itis.datregistration.services;

import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {
    boolean signIn(HttpServletRequest request);
    void signUp(HttpServletRequest request);
    void changePassword(HttpServletRequest request);
}
