package com.iumutdikbasan.weatherapp.exception.weatherexceptions;

public class WeatherDataNotParsedException extends RuntimeException{
    public WeatherDataNotParsedException(String message){
        super(message);
    }
}
