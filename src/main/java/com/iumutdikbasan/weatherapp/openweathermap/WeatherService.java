package com.iumutdikbasan.weatherapp.openweathermap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iumutdikbasan.weatherapp.errormessages.WeatherErrorMessage;
import com.iumutdikbasan.weatherapp.exception.weatherexceptions.WeatherDataNotParsedException;
import com.iumutdikbasan.weatherapp.general.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    public WeatherData getWeatherForecast(String cityName, String unit){
        String weatherForecast = weatherClient.getWeatherForecast(cityName, apiKey, unit);
        return parseWeatherData(weatherForecast);
    }


    private WeatherData parseWeatherData(String weatherForecast) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(weatherForecast, WeatherData.class);
        }
        catch (JsonProcessingException e) {
            throw new WeatherDataNotParsedException(WeatherErrorMessage.WEATHER_DATA_PARSING_ERROR.getMessage() + e);
        }
    }

}