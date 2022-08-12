package com.ficticiajava.main.exception;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(String message) {
        super(message);
    }
}