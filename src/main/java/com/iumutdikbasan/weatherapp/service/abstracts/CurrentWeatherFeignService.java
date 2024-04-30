package com.iumutdikbasan.weatherapp.service.abstracts;

import com.iumutdikbasan.weatherapp.dto.CurrentWeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="deneme2", url= "https://api.openweathermap.org/data/2.5/weather")
public interface CurrentWeatherFeignService {


    @GetMapping
    CurrentWeatherDTO currentWeatherData(@RequestParam String q, @RequestParam String appid, @RequestParam String units);


}