package com.iumutdikbasan.weatherapp.errormessages;

import com.iumutdikbasan.weatherapp.general.BaseErrorMessage;

public enum JwtErrorMessage implements BaseErrorMessage {
    JWT_GENERATION_FAILED("JWT token genration failed: ");

    private final String message;

    JwtErrorMessage(String message) {
        this.message =message;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String toString() {
        return message;
    }
}
