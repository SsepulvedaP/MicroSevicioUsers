package com.MicroService.MicroServiceUsers.Utils;

public class Constants {
    public static final String INVALID_EMAIL_FORMAT = "El formato del correo electrónico es inválido.";
    public static final String INVALID_PHONE_FORMAT = "El número de teléfono debe contener un máximo de 13 caracteres";
    public static final String INVALID_DOCUMENT_FORMAT = "El documento de identidad debe ser numérico.";
    public static final String UNDERAGE_USER = "El usuario debe ser mayor de edad.";
    public static final String ENCRYPTION_ERROR = "Error al encriptar la contraseña.";
    public static final String USER_ALREADY_EXISTS = "El usuario ya existe.";
    public static final String USER_CREATED_SUCCESSFULLY = "Usuario creado exitosamente.";
    public static final String USER_NOT_FOUND = "Usuario no encontrado.";
    public static final String ROLE_NOT_FOUND = "Rol no encontrado.";
    public static final String INVALID_ROLE = "Rol inválido.";
    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_+&*-]+(\\.[a-zA-Z0-9_+&*-]+)?@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7}+(\\.[a-zA-Z]{2,7}+)?$";
    public static final String PHONE_VALIDATION_REGEX = "^\\+?[0-9]{10,13}$";
    public static final String EMAIL_ALREADY_EXISTS = "El correo electrónico ya está registrado.";
    public static final String DOCUMENT_ALREADY_EXISTS = "El documento de identidad ya está registrado.";
    public static final String USER_OR_PASSWORD_INCORRECT = "Correo o contraseña incorrectos.";
    public static final String INVALID_AUTH = "Erro de autenticación.";
    public static final String INVALID_PERMISSION = "No tienes permisos para realizar esta acción.";
}
