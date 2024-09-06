package com.MicroService.MicroServiceUsers.Infrastructure.Exception;

import com.MicroService.MicroServiceUsers.Domain.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            DuplicateDocumentException.class,
            RoleNotFoundException.class,
            InvalidAgeException.class,
            InvalidDocumentException.class,
            InvalidEmailException.class,
            InvalidPhoneException.class,
            InvalidUserException.class,
            UserAlreadyExistsException.class,
            UserNotFoundException.class,
            DuplicateEmailException.class
    })
    public ResponseEntity<ErrorResponse> handleValidationExceptions(Exception ex) {
        List<String> errorMessages = new ArrayList<>();

        if (ex instanceof DuplicateEmailException) {
            errorMessages.add(ex.getMessage());
        }
        if (ex instanceof InvalidAgeException) {
            errorMessages.add(ex.getMessage());
        }
        if (ex instanceof InvalidDocumentException) {
            errorMessages.add(ex.getMessage());
        }
        if (ex instanceof InvalidEmailException) {
            errorMessages.add(ex.getMessage());
        }
        if (ex instanceof InvalidPhoneException) {
            errorMessages.add(ex.getMessage());
        }
        if (ex instanceof InvalidUserException) {
            errorMessages.add(ex.getMessage());
        }
        if (ex instanceof UserAlreadyExistsException) {
            errorMessages.add(ex.getMessage());
        }
        if (ex instanceof UserNotFoundException) {
            errorMessages.add(ex.getMessage());
        }
        if(ex instanceof RoleNotFoundException){
            errorMessages.add(ex.getMessage());
        }
        if(ex instanceof DuplicateDocumentException){
            errorMessages.add(ex.getMessage());
        }
        // Otros ifs para las demás excepciones...

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}

