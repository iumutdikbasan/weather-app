package com.iumutdikbasan.weatherapp.errormessages;


import com.iumutdikbasan.weatherapp.general.BaseErrorMessage;

public enum UserErrorMessage implements BaseErrorMessage {

    USERNAME_ALREADY_TAKEN("Username already taken");


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