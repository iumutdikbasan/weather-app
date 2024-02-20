package com.iumutdikbasan.weatherapp.exception.weatherexceptions;

public class WeatherDataNotFoundException extends RuntimeException{
    public WeatherDataNotFoundException(String message){
        super(message);
    }
}
