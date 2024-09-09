package com.MicroService.MicroServiceUsers.Infrastructure.Configuration.SecurityConfig;


import com.MicroService.MicroServiceUsers.Application.Auth.AuthenticationRequest;
import com.MicroService.MicroServiceUsers.Application.Auth.AuthenticationResponse;
import com.MicroService.MicroServiceUsers.Domain.Exception.InvalidUserException;
import com.MicroService.MicroServiceUsers.Infrastructure.Configuration.JwtConfiguration.JwtService;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.AuthenticationException;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.UserNotFoundException;
import com.MicroService.MicroServiceUsers.Infrastructure.Jpa.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;

import static com.MicroService.MicroServiceUsers.Utils.Constants.*;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

            var jwtToken = jwtService.generate(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (BadCredentialsException e) {
            throw new InvalidUserException(USER_OR_PASSWORD_INCORRECT);
        } catch (AuthenticationException e) {
            throw new InvalidUserException(INVALID_AUTH);
        }
    }


}
