package com.MicroService.MicroServiceUsers.Domain.Exception;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }
}
