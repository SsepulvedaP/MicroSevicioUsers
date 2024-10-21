package com.MicroService.MicroServiceUsers.Infrastructure.Input.Rest;

import com.MicroService.MicroServiceUsers.Application.Auth.AuthenticationRequest;
import com.MicroService.MicroServiceUsers.Application.Auth.AuthenticationResponse;

import com.MicroService.MicroServiceUsers.Infrastructure.Configuration.SecurityConfig.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticateController {

    private final AuthenticationService service;

    @Operation(summary = "Autenticar usuario",
            description = "Autentica un usuario existente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Petición inválida",
                    content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return  ResponseEntity.ok(service.authenticate(request));
    }


}
