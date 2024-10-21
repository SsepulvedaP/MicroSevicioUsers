package com.MicroService.MicroServiceUsers.Application.Handler;

import com.MicroService.MicroServiceUsers.Application.Dto.Request.RegisterRequest;
import com.MicroService.MicroServiceUsers.Application.Mapper.IUserRequestMapper;
import com.MicroService.MicroServiceUsers.Domain.Api.IUserServicePort;
import com.MicroService.MicroServiceUsers.Domain.Models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.MicroService.MicroServiceUsers.Utils.Constants.ROLE_AUX_BODEGA;
import static com.MicroService.MicroServiceUsers.Utils.Constants.ROLE_CLIENT;


@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    public void createUserAuxBodega(RegisterRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);
        userServicePort.createAuxBodega(user);
    }

    public void createUserClient(RegisterRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);
        userServicePort.createClient(user);
    }
}
