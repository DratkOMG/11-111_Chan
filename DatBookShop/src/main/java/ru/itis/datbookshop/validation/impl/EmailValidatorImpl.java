package ru.itis.datbookshop.validation.impl;

import ru.itis.datbookshop.exceptions.EmailValidatorException;
import ru.itis.datbookshop.repositories.user.AccountsRepository;
import ru.itis.datbookshop.repositories.user.impl.AccountsRepositoryImpl;
import ru.itis.datbookshop.validation.EmailValidator;

public class EmailValidatorImpl implements EmailValidator {
    private final AccountsRepository accountsRepository = new AccountsRepositoryImpl();

    @Override
    public void validate(String email) throws EmailValidatorException {
        if (this.accountsRepository.getAccountByEmail(email) != null) {
            throw new EmailValidatorException("Email used");
        }
    }

    public static void main(String[] args) {
        String a = "123";
        System.out.println(a.length());
    }
}
