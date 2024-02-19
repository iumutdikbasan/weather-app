package com.iumutdikbasan.weatherapp.controller.contract;

import com.iumutdikbasan.weatherapp.dto.city.request.CitySaveRequestDTO;
import com.iumutdikbasan.weatherapp.dto.city.response.CityResponseDTO;

import java.util.List;

public interface CityControllerContract {
    List<CityResponseDTO> findByUserId(Long userId);
    List<CityResponseDTO> findCityByUserId();

    CityResponseDTO save(CitySaveRequestDTO citySaveRequestDTO);
    void delete(Long id);
}
