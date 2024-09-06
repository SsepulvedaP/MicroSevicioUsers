package com.MicroService.MicroServiceUsers.Application.Dto.Request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

import static com.MicroService.MicroServiceUsers.Utils.Constants.INVALID_PHONE_FORMAT;
import static com.MicroService.MicroServiceUsers.Utils.Constants.PHONE_VALIDATION_REGEX;


@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @NonNull
    private String name;
    @NotBlank
    @NonNull
    private String lastName;
    @NotBlank
    @NonNull
    @Email
    private String email;
    private String password;
    @NotBlank
    @NonNull
    @Size(min = 1, max = 13)
    @Pattern(regexp = PHONE_VALIDATION_REGEX, message = INVALID_PHONE_FORMAT)
    private String phone;
    @NotBlank
    @NonNull
    @Positive
    private String document;
    private LocalDate birthDate;
    @NotBlank
    private String role;
}
