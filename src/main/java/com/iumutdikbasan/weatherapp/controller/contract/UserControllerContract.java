package com.iumutdikbasan.weatherapp.controller.contract;

import com.iumutdikbasan.weatherapp.dto.user.response.UserResponseDTO;
import com.iumutdikbasan.weatherapp.openweathermap.WeatherData;

import java.util.List;
import java.util.Map;

public interface UserControllerContract {
//    UserDTO save(UserSaveRequestDTO userSaveRequestDTO);

    Map<String, WeatherData> findUsersSavedCitiesWeatherData(String unit);
    List<UserResponseDTO> findAll();
}
