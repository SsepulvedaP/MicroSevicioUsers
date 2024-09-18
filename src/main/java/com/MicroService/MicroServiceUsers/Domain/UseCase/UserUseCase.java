package com.MicroService.MicroServiceUsers.Domain.UseCase;

import com.MicroService.MicroServiceUsers.Domain.Api.IUserServicePort;
import com.MicroService.MicroServiceUsers.Domain.Exception.*;
import com.MicroService.MicroServiceUsers.Domain.Models.Role;
import com.MicroService.MicroServiceUsers.Domain.Models.User;
import com.MicroService.MicroServiceUsers.Domain.Spi.IRolPersistencePort;
import com.MicroService.MicroServiceUsers.Domain.Spi.IUserPersistencePort;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.RoleNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.MicroService.MicroServiceUsers.Utils.Constants.*;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolPersistencePort rolPersistencePort;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolPersistencePort rolPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolPersistencePort = rolPersistencePort;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void createUser(User user) {
        validateUser(user);
        user.setPassword(encryptPassword(user.getPassword()));
        userPersistencePort.createUser(user);
    }

    @Override
    public void createAuxBodega(User user) {
        Optional<Role> role = rolPersistencePort.getRolByName(ROLE_AUX_BODEGA);
        if (role.isEmpty()) {
            throw new RoleNotFoundException(ROLE_NOT_FOUND);
        }
        user.setRole(role.get());
        createUser(user);
    }

    @Override
    public void createClient(User user) {
        Optional<Role> role = rolPersistencePort.getRolByName(ROLE_CLIENT);
        if (role.isEmpty()) {
            throw new RoleNotFoundException(ROLE_NOT_FOUND);
        }
        user.setRole(role.get());
        createUser(user);
    }



    @Override
    public boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_VALIDATION_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches() && phone.length() <= 13;
    }

    @Override
    public boolean validateDocument(String document) {
        return document.matches("\\d+");
    }

    @Override
    public boolean validateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.minusYears(18).isAfter(birthDate);
    }

    private void validateUser(User user) {
        if (!validateEmail(user.getEmail())) {
            throw new InvalidEmailException(INVALID_EMAIL_FORMAT);
        }
        if (!validatePhone(user.getPhone())) {
            throw new InvalidPhoneException(INVALID_PHONE_FORMAT);
        }
        if (!validateDocument(user.getDocument())) {
            throw new InvalidDocumentException(INVALID_DOCUMENT_FORMAT);
        }
        if (!validateAge(user.getBirthDate())) {
            throw new InvalidAgeException(UNDERAGE_USER);
        }
    }
}
