package ru.itis.datbookshop.validation.impl;

import ru.itis.datbookshop.exceptions.PasswordValidatorException;
import ru.itis.datbookshop.validation.PasswordValidator;

public class PasswordByLengthValidatorImpl implements PasswordValidator {
    private final int minLength = 8;

    @Override
    public void validate(String password) throws PasswordValidatorException {
        if (password.length() < minLength) {
            throw new PasswordValidatorException("Short password");
        }
    }
}
