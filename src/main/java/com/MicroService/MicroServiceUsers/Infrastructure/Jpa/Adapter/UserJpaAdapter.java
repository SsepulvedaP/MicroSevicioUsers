package com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Adapter;

import com.MicroService.MicroServiceUsers.Domain.Models.User;
import com.MicroService.MicroServiceUsers.Domain.Spi.IUserPersistencePort;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.DuplicateDocumentException;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.DuplicateEmailException;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.UserNotFoundException;
import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Entity.UserEntity;
import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Mapper.UserEntityMapper;
import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;


import java.util.Optional;

import static com.MicroService.MicroServiceUsers.Utils.Constants.*;


@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toUser)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public void createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateEmailException(EMAIL_ALREADY_EXISTS);
        }
        if(userRepository.findByDocument(user.getDocument()).isPresent()){
            throw new DuplicateDocumentException(DOCUMENT_ALREADY_EXISTS);
        }

        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        userEntityMapper.toUser(userRepository.save(userEntity));
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
