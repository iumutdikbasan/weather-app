package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.controller.contract.UserControllerContract;
import com.iumutdikbasan.weatherapp.dto.user.response.UserResponseDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.errormessages.WeatherErrorMessage;
import com.iumutdikbasan.weatherapp.exception.weatherexceptions.WeatherDataNotFoundException;
import com.iumutdikbasan.weatherapp.kafka.service.KafkaService;
import com.iumutdikbasan.weatherapp.mapper.UserMapper;
import com.iumutdikbasan.weatherapp.openweathermap.WeatherData;
import com.iumutdikbasan.weatherapp.openweathermap.WeatherService;
import com.iumutdikbasan.weatherapp.security.user.User;
import com.iumutdikbasan.weatherapp.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService service;
    private final UserMapper mapper;
    private final KafkaService kafkaService;
    private final WeatherService weatherService;


    @Override
    public Map<String, WeatherData> findUsersSavedCitiesWeatherData(String unit) {
        User user = service.extractUser();
        List<City> cities = user.getCities();
        Map<String, WeatherData> citiesData= new HashMap<>();

        for (City city: cities){
            try {
                citiesData.put(city.getName(),weatherService.getWeatherForecast(city.getName(),unit));
            }
            catch (Exception e){
                throw new WeatherDataNotFoundException(WeatherErrorMessage.WEATHER_NOT_FOUND.getMessage());
            }
        }
        kafkaService.sendMessageInfo("Weather data returned", "logs");
        return citiesData;
    }

    @Override
    public List<UserResponseDTO> findAll() {
        List<User> userList = service.findAll();
        kafkaService.sendMessageInfo("Users returned!", "logs");
        return mapper.convertToUserDtoList(userList);
    }
}
