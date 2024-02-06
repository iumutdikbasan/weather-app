package com.iumutdikbasan.weatherapp.controller.contract;

import com.iumutdikbasan.weatherapp.dto.city.CityDTO;
import com.iumutdikbasan.weatherapp.dto.city.CitySaveRequestDTO;

import java.util.List;

public interface CityControllerContract {
    List<CityDTO> findByUserId(Long userId);
    CityDTO save(CitySaveRequestDTO citySaveRequestDTO);
    void delete(Long id);
}
