package com.iumutdikbasan.weatherapp.mapper;


import com.iumutdikbasan.weatherapp.dto.requests.CityRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.CityResponseDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CityMapper {


    City toCity(CityRequestDTO cityRequestDTO);
    CityRequestDTO toCityRequestDTO(City city);

    City toCity(CityResponseDTO cityResponseDTO);
    CityResponseDTO toCityResponseDTO(City city);
}
