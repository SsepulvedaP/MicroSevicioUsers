package com.MicroService.MicroServiceUsers.Domain.Spi;


import com.MicroService.MicroServiceUsers.Domain.Models.Role;

import java.util.Optional;

public interface IRolPersistencePort {
    Optional<Role> getRolByName(String role);
}
