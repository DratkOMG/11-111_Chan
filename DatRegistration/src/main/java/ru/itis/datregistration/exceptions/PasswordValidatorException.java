package ru.itis.datregistration.exceptions;

public class PasswordValidatorException extends RuntimeException{
    public PasswordValidatorException(String message) {
        super(message);
    }
}
