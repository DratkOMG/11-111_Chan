package ru.itis.datregistration.validation.impl;

import ru.itis.datregistration.exceptions.PasswordValidatorException;
import ru.itis.datregistration.validation.PasswordValidator;

public class PasswordByLengthValidatorImpl implements PasswordValidator {
    private final int minLength = 8;

    @Override
    public void validate(String password) throws PasswordValidatorException {
        if (password.length() < minLength) {
            throw new PasswordValidatorException("Short password");
        }
    }
}
