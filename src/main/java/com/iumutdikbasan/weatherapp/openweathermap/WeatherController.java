package com.iumutdikbasan.weatherapp.openweathermap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/data")
    public WeatherData getWeather(@RequestParam String cityName , @RequestParam String unit) {
        return weatherService.getWeatherForecast(cityName, unit);
    }
}
