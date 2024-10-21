package com.MicroService.MicroServiceUsers.Infrastructure.Exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
