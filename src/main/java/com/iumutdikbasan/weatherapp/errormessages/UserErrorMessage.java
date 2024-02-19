package com.iumutdikbasan.weatherapp.errormessages;


import com.iumutdikbasan.weatherapp.general.BaseErrorMessage;

public enum UserErrorMessage implements BaseErrorMessage {

    EMAIL_ALREADY_TAKEN("Email already taken : "),
    USERS_NOT_FOUND("Users not found : "),
    AUTHENTICATION_FAILED("Authentication failed : "),
    INVALID_EMAIL_OR_PASSWORD("Invalid email or password : "),
    USER_NOT_CREATED("User not created ");


    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}