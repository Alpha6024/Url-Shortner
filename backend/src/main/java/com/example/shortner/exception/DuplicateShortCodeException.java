package com.example.shortner.exception;

public class DuplicateShortCodeException extends RuntimeException {

    public DuplicateShortCodeException(String message) {
        super(message);
    }
}