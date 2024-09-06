package com.MicroService.MicroServiceUsers.Domain.Api;

import com.MicroService.MicroServiceUsers.Domain.Models.User;

import java.time.LocalDate;


public interface IUserServicePort {
    void createUser(User user);

    boolean validateEmail(String email);

    String encryptPassword(String password);

    boolean validatePhone(String phone);

    boolean validateDocument(String document);

    boolean validateAge(LocalDate birthDate);
}
