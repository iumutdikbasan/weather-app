package com.iumutdikbasan.weatherapp.exception.userexception;

public class AuthenticationFailedException extends RuntimeException{

    public AuthenticationFailedException(String message){
        super(message);
    }
}
