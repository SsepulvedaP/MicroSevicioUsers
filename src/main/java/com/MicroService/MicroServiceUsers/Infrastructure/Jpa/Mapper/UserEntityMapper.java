package com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Mapper;


import com.MicroService.MicroServiceUsers.Domain.Models.User;
import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {


    UserEntity toUserEntity(User user);


    User toUser(UserEntity userEntity);
}
