package ru.itis.datbookshop.repositories.user;


import ru.itis.datbookshop.models.Account;

import java.util.List;

public interface AccountsRepository {
    void changePassword(Account account);

    void signUp(Account account);

    List<Account> getAll();

    Account signIn(String email, String password);

    String getPassword(String email);

    Account getAccountByEmail(String email);

    Account getAccountById(long id);

    List<Account> getNext10Account(int amount);

    void updateSeller(long eId, String seller);

    void deleteAccById(long eId);
}
