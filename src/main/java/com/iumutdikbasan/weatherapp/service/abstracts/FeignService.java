package com.iumutdikbasan.weatherapp.service.abstracts;

import com.iumutdikbasan.weatherapp.dto.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name ="deneme", url= "https://api.openweathermap.org/data/2.5/forecast")
public interface FeignService {

    @GetMapping
    WeatherDTO weatherData(@RequestParam String q, @RequestParam String appid, @RequestParam String units);
}