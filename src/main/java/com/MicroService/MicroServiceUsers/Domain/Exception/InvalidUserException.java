package com.MicroService.MicroServiceUsers.Domain.Exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}
