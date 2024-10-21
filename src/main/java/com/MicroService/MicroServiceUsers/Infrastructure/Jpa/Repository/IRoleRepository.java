package com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Repository;


import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
