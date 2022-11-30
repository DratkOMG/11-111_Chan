package ru.itis.datbookshop.exceptions;

public class EmailValidatorException extends RuntimeException {
    public EmailValidatorException(String message) {
        super(message);
    }
}
