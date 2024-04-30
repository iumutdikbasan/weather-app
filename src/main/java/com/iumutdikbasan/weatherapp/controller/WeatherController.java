package com.iumutdikbasan.weatherapp.controller;

import com.iumutdikbasan.weatherapp.dto.CurrentWeatherDTO;
import com.iumutdikbasan.weatherapp.dto.WeatherDTO;
import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.result.generalResult.DataResult;
import com.iumutdikbasan.weatherapp.result.generalResult.SuccessDataResult;
import com.iumutdikbasan.weatherapp.service.concretes.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    private final KafkaService kafkaService;

    @GetMapping("/data")
    public ResponseEntity<DataResult<WeatherDTO>> getFromController(@RequestParam String cityName) {

        String record = String.format("[%s:%d] Forecast Weather data requested for %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityName);
        kafkaService.sendMessage(record,"debug");
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",weatherService.getWeather(cityName)));
    }

    @GetMapping("/currentWeather")
    public ResponseEntity<DataResult<CurrentWeatherDTO>> getCurrentWeather(@RequestParam String cityName) {
        String record = String.format("[%s:%d] Current weather data requested for %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityName);
        kafkaService.sendMessage(record,"debug");
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResult<>("Listelendi",weatherService.getCurrentWeather(cityName)));
    }




}