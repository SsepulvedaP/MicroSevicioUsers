package com.MicroService.MicroServiceUsers.Domain.Spi;

import com.MicroService.MicroServiceUsers.Domain.Models.User;

public interface IUserPersistencePort {
    User getUserByEmail(String email);
    void createUser(User user);
}
