package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.dto.user.response.UserResponseDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.kafka.service.KafkaService;
import com.iumutdikbasan.weatherapp.mapper.UserMapper;
import com.iumutdikbasan.weatherapp.openweathermap.WeatherData;
import com.iumutdikbasan.weatherapp.openweathermap.WeatherService;
import com.iumutdikbasan.weatherapp.security.user.User;
import com.iumutdikbasan.weatherapp.service.UserEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserControllerContractImplTest {
    @Mock
    private UserEntityService userEntityService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private KafkaService kafkaService;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private UserControllerContractImpl userControllerContract;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        Mockito.when(userEntityService.findAll()).thenReturn(userList);

        List<UserResponseDTO> userDtoList = new ArrayList<>();
        userDtoList.add(new UserResponseDTO(1L, "username", "password"));
        Mockito.when(userMapper.convertToUserDtoList(userList)).thenReturn(userDtoList);

        List<UserResponseDTO> result = userControllerContract.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Mockito.verify(kafkaService).sendMessageInfo(Mockito.anyString(), Mockito.anyString());
    }


    @Test
    void testFindUsersSavedCitiesWeatherData() {
        User user = new User();
        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setName("Istanbul");
        cities.add(city);
        user.setCities(cities);

        String unit = "metric";
        Map<String, WeatherData> expectedData = new HashMap<>();
        expectedData.put(city.getName(), new WeatherData());

        Mockito.when(userEntityService.extractUser()).thenReturn(user);
        Mockito.when(weatherService.getWeatherForecast(city.getName(), unit)).thenReturn(new WeatherData());

        Map<String, WeatherData> result = userControllerContract.findUsersSavedCitiesWeatherData(unit);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(expectedData, result);
        Mockito.verify(kafkaService).sendMessageInfo(Mockito.anyString(), Mockito.anyString());
    }
}
