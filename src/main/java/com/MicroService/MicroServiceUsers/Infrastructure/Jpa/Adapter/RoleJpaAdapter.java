package com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Adapter;

import com.MicroService.MicroServiceUsers.Domain.Models.Role;
import com.MicroService.MicroServiceUsers.Domain.Spi.IRolPersistencePort;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.RoleNotFoundException;
import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Entity.RoleEntity;
import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Repository.IRoleRepository;

import java.util.Optional;

import static com.MicroService.MicroServiceUsers.Utils.Constants.ROLE_NOT_FOUND;

public class RoleJpaAdapter implements IRolPersistencePort {

    private final IRoleRepository roleRepository;


    public RoleJpaAdapter(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> getRolByName(String role) {
        return roleRepository.findByName(role)
                .map(roleEntity -> new Role(roleEntity.getId(), roleEntity.getName()));
    }
}
