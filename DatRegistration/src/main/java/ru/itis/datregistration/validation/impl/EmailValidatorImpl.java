package ru.itis.datregistration.validation.impl;

import ru.itis.datregistration.repositories.AccountsRepository;
import ru.itis.datregistration.repositories.impl.AccountsRepositoryImpl;
import ru.itis.datregistration.exceptions.EmailValidatorException;
import ru.itis.datregistration.validation.EmailValidator;

public class EmailValidatorImpl implements EmailValidator {
    private final AccountsRepository accountsRepository = new AccountsRepositoryImpl();

    @Override
    public void validate(String email) throws EmailValidatorException {
        if (this.accountsRepository.findByEmail(email)) {
            throw new EmailValidatorException("Email used");
        }
    }

    public static void main(String[] args) {
        String a = "123";
        System.out.println(a.length());
    }
}
