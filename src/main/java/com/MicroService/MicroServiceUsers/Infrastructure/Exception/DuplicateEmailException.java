package com.MicroService.MicroServiceUsers.Infrastructure.Exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
