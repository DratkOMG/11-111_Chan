package ru.itis.datregistration.exceptions;

public class EmailValidatorException extends RuntimeException {
    public EmailValidatorException(String message) {
        super(message);
    }
}
