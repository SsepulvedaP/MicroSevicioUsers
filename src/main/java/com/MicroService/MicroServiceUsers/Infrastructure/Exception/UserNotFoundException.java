package com.MicroService.MicroServiceUsers.Infrastructure.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
