package com.iumutdikbasan.weatherapp.errormessages;

import com.iumutdikbasan.weatherapp.general.BaseErrorMessage;

public enum WeatherErrorMessage implements BaseErrorMessage {

    WEATHER_NOT_FOUND("Weather not found"),
    WEATHER_DATA_PARSING_ERROR("Weather data parsing error");
    private final String message;

    WeatherErrorMessage(String message) {
        this.message = message;
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
