package com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Repository;


import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocument(String document);
    Optional <UserEntity >findByName(String name);

}
