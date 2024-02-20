package com.iumutdikbasan.weatherapp.exception.jwtexceptions.exceptiohandler;

public class JWTNotCreatedException extends RuntimeException{
    public JWTNotCreatedException(String message){
        super(message);
    }
}
