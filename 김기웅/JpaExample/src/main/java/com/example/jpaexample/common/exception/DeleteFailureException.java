package com.example.jpaexample.common.exception;

public class DeleteFailureException extends RuntimeException {
    public DeleteFailureException(String message) {
        super(message);
    }
}
