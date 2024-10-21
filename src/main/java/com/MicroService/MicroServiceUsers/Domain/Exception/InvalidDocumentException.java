package com.MicroService.MicroServiceUsers.Domain.Exception;

public class InvalidDocumentException extends RuntimeException {
    public InvalidDocumentException(String message) {
        super(message);
    }
}
