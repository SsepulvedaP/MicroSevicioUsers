package com.MicroService.MicroServiceUsers.Infrastructure.Input.Rest;


import com.MicroService.MicroServiceUsers.Application.Dto.Request.RegisterRequest;
import com.MicroService.MicroServiceUsers.Application.Dto.Response.RegisterResponse;
import com.MicroService.MicroServiceUsers.Application.Handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.MicroService.MicroServiceUsers.Utils.Constants.USER_CREATED_SUCCESSFULLY;

@RestController
@RequestMapping("/register")
@Tag(name = "User Controller", description = "Operations related to user management")
@RequiredArgsConstructor
public class RegisterRestController {

    private final IUserHandler userHandler;


    @Operation(summary = "Crear nuevo usuario",
            description = "Crea un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = USER_CREATED_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = "Petición inválida",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PostMapping
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        userHandler.createUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(USER_CREATED_SUCCESSFULLY));
    }



}
