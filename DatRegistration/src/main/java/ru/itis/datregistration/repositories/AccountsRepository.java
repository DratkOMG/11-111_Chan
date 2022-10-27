package ru.itis.datregistration.repositories;

import ru.itis.datregistration.models.Account;

public interface AccountsRepository {
    void changePassword(Account account);
    void signUp(Account account);
    Account signIn(String email, String password);
    boolean findByEmail(String email);
}
