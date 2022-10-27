package ru.itis.datregistration.repositories;

import ru.itis.datregistration.models.Account;
import ru.itis.datregistration.models.User;

public interface UsersRepository {
    void createProfile(Account account);
    User getProfileByEmail(String email);
    User getProfileByUsername(String username);
    User getProfileByNumberPhone(long numberPhone);
    void updateUsername(String username, String email);
    void updateNumberPhone(long numberPhone, String email);
}
