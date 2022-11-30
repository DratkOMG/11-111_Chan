package ru.itis.datbookshop.services.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datbookshop.models.Account;

import java.util.List;

public interface AccountService {
    boolean signIn(HttpServletRequest request, HttpServletResponse response);

    void signUp(HttpServletRequest request);

    void changePassword(HttpServletRequest request);

    List<Account> findAll();

    List<Account> findNext10(int amount);

    Account findAccountById(long id);

    Account findAccountByEmail(String email);

    void updateSeller(long eId, String seller);

    void deleteById(long eId);
}
