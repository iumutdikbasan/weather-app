package com.iumutdikbasan.weatherapp.service.concretes;

import com.iumutdikbasan.weatherapp.dto.CurrentWeatherDTO;
import com.iumutdikbasan.weatherapp.dto.WeatherDTO;
import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.service.abstracts.CurrentWeatherFeignService;
import com.iumutdikbasan.weatherapp.service.abstracts.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${weather.apikey}")
    private String apiKey;

    @Value("${weather.units}")
    private String units;
    private final FeignService feignService;
    private final CurrentWeatherFeignService currentWeatherFeignService;



    public WeatherDTO getWeather(String cityName) {

        WeatherDTO responseData = feignService.weatherData(cityName,apiKey,units);

        if (responseData != null)
            return responseData;
        else
            throw new MyException("Listeleme Başarısız");

    }

    public CurrentWeatherDTO getCurrentWeather(String cityName) {

        CurrentWeatherDTO responseData = currentWeatherFeignService.currentWeatherData(cityName,apiKey,units);
        if(responseData != null)
            return responseData;
        else
            throw new MyException("Listeleme Başarısız");
    }


}