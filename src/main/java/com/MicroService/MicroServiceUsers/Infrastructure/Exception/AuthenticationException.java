package com.MicroService.MicroServiceUsers.Infrastructure.Exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
