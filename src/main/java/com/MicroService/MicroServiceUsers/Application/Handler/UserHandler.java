package com.MicroService.MicroServiceUsers.Application.Handler;

import com.MicroService.MicroServiceUsers.Application.Dto.Request.RegisterRequest;
import com.MicroService.MicroServiceUsers.Application.Mapper.IUserRequestMapper;
import com.MicroService.MicroServiceUsers.Domain.Api.IUserServicePort;
import com.MicroService.MicroServiceUsers.Domain.Models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    public void createUser(RegisterRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);
        userServicePort.createUser(user);
    }
}
