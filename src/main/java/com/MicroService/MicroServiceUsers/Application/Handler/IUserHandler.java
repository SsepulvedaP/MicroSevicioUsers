package com.MicroService.MicroServiceUsers.Application.Handler;

import com.MicroService.MicroServiceUsers.Application.Dto.Request.RegisterRequest;

public interface IUserHandler {

    void createUserAuxBodega(RegisterRequest registerRequest);
    void createUserClient(RegisterRequest registerRequest);
}
