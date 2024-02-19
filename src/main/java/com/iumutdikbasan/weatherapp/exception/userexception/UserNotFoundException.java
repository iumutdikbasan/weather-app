package com.iumutdikbasan.weatherapp.exception.userexception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
