package com.MicroService.MicroServiceUsers.Infrastructure.Exception;

public class DuplicateDocumentException extends RuntimeException {
    public DuplicateDocumentException(String message) {
        super(message);
    }
}
