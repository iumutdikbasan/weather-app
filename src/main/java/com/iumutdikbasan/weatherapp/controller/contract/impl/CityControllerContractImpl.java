package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.controller.contract.CityControllerContract;
import com.iumutdikbasan.weatherapp.dto.city.CityDTO;
import com.iumutdikbasan.weatherapp.dto.city.CitySaveRequestDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.mapper.CityMapper;
import com.iumutdikbasan.weatherapp.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityControllerContractImpl implements CityControllerContract {

    private final CityService service;
    private final CityMapper mapper;

    @Override
    public List<CityDTO> findByUserId(Long userId) {
        List<City> cityList = service.findByUserId(userId);
        return mapper.convertToCityDtoList(cityList);

    }

    @Override
    public CityDTO save(CitySaveRequestDTO citySaveRequestDTO) {
        City city = mapper.convertToCity(citySaveRequestDTO);
        service.save(city);
        return mapper.convertToCityDto(city);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}