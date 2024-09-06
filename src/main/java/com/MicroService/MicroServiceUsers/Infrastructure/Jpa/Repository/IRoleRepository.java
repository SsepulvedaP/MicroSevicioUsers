package com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Repository;


import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
