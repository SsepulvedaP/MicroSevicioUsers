package com.MicroService.MicroServiceUsers.Domain.UseCase;

import com.MicroService.MicroServiceUsers.Domain.Exception.InvalidAgeException;
import com.MicroService.MicroServiceUsers.Domain.Exception.InvalidDocumentException;
import com.MicroService.MicroServiceUsers.Domain.Exception.InvalidEmailException;
import com.MicroService.MicroServiceUsers.Domain.Exception.InvalidPhoneException;
import com.MicroService.MicroServiceUsers.Domain.Models.Role;
import com.MicroService.MicroServiceUsers.Domain.Models.User;
import com.MicroService.MicroServiceUsers.Domain.Spi.IRolPersistencePort;
import com.MicroService.MicroServiceUsers.Domain.Spi.IUserPersistencePort;
import com.MicroService.MicroServiceUsers.Infrastructure.Exception.RoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolPersistencePort rolPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_withValidUser_createsUserSuccessfully() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setDocument("123456");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setPassword("password");
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);

        when(rolPersistencePort.getRolByName("USER")).thenReturn(Optional.of(role));

        userUseCase.createUser(user);

        verify(userPersistencePort, times(1)).createUser(user);
    }

    @Test
    void createUser_withInvalidEmail_throwsInvalidEmailException() {
        User user = new User();
        user.setEmail("invalid-email");
        user.setPhone("1234567890");
        user.setDocument("123456");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setPassword("password");
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);

        assertThrows(InvalidEmailException.class, () -> userUseCase.createUser(user));
    }

    @Test
    void createUser_withInvalidPhone_throwsInvalidPhoneException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhone("invalid-phone");
        user.setDocument("123456");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setPassword("password");
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);

        assertThrows(InvalidPhoneException.class, () -> userUseCase.createUser(user));
    }

    @Test
    void createUser_withInvalidDocument_throwsInvalidDocumentException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setDocument("invalid-document");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setPassword("password");
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);

        assertThrows(InvalidDocumentException.class, () -> userUseCase.createUser(user));
    }

    @Test
    void createUser_withUnderageUser_throwsInvalidAgeException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setDocument("123456");
        user.setBirthDate(LocalDate.now().minusYears(17));
        user.setPassword("password");
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);

        assertThrows(InvalidAgeException.class, () -> userUseCase.createUser(user));
    }

    @Test
    void createUser_withNonExistentRole_throwsRoleNotFoundException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setDocument("123456");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setPassword("password");
        Role role = new Role();
        role.setName("NON_EXISTENT_ROLE");
        user.setRole(role);

        when(rolPersistencePort.getRolByName("NON_EXISTENT_ROLE")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> userUseCase.createUser(user));
    }

    @Test
    void validateDocument_withValidDocument_returnsTrue() {
        String validDocument = "123456";
        assertTrue(userUseCase.validateDocument(validDocument));
    }

    @Test
    void validateDocument_withInvalidDocument_returnsFalse() {
        String invalidDocument = "abc123";
        assertFalse(userUseCase.validateDocument(invalidDocument));
    }

    @Test
    void validateDocument_withEmptyDocument_returnsFalse() {
        String emptyDocument = "";
        assertFalse(userUseCase.validateDocument(emptyDocument));
    }


    @Test
    void validateAge_withValidAge_returnsTrue() {
        LocalDate validBirthDate = LocalDate.now().minusYears(20);
        assertTrue(userUseCase.validateAge(validBirthDate));
    }

    @Test
    void validateAge_withUnderage_returnsFalse() {
        LocalDate underageBirthDate = LocalDate.now().minusYears(17);
        assertFalse(userUseCase.validateAge(underageBirthDate));
    }

    @Test
    void validateAge_withExactly18YearsOld_returnsFalse() {
        LocalDate exactly18YearsOld = LocalDate.now().minusYears(18);
        assertFalse(userUseCase.validateAge(exactly18YearsOld));
    }

    @Test
    void validateAge_withNullBirthDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userUseCase.validateAge(null));
    }

    @Test
    void validateEmail_withValidEmail_returnsTrue() {
        String validEmail = "test@example.com";
        assertTrue(userUseCase.validateEmail(validEmail));
    }

    @Test
    void validateEmail_withInvalidEmail_returnsFalse() {
        String invalidEmail = "invalid-email";
        assertFalse(userUseCase.validateEmail(invalidEmail));
    }

    @Test
    void validateEmail_withEmptyEmail_returnsFalse() {
        String emptyEmail = "";
        assertFalse(userUseCase.validateEmail(emptyEmail));
    }

    @Test
    void encryptPassword_withValidPassword_returnsEncryptedPassword() {
        String password = "password";
        String encryptedPassword = userUseCase.encryptPassword(password);
        assertNotNull(encryptedPassword);
        assertNotEquals(password, encryptedPassword);
    }

    @Test
    void encryptPassword_withEmptyPassword_returnsEncryptedPassword() {
        String password = "";
        String encryptedPassword = userUseCase.encryptPassword(password);
        assertNotNull(encryptedPassword);
        assertNotEquals(password, encryptedPassword);
    }


    @Test
    void validatePhone_withValidPhone_returnsTrue() {
        String validPhone = "1234567890";
        assertTrue(userUseCase.validatePhone(validPhone));
    }

    @Test
    void validatePhone_withInvalidPhone_returnsFalse() {
        String invalidPhone = "invalid-phone";
        assertFalse(userUseCase.validatePhone(invalidPhone));
    }

    @Test
    void validatePhone_withTooLongPhone_returnsFalse() {
        String tooLongPhone = "12345678901234";
        assertFalse(userUseCase.validatePhone(tooLongPhone));
    }

    @Test
    void validatePhone_withEmptyPhone_returnsFalse() {
        String emptyPhone = "";
        assertFalse(userUseCase.validatePhone(emptyPhone));
    }

   

}