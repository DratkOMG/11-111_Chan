package ru.itis.datbookshop.exceptions;

public class PasswordValidatorException extends RuntimeException{
    public PasswordValidatorException(String message) {
        super(message);
    }
}
