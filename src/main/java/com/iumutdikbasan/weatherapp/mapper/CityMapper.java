package com.iumutdikbasan.weatherapp.mapper;

import com.iumutdikbasan.weatherapp.dto.city.CityDTO;
import com.iumutdikbasan.weatherapp.dto.city.CitySaveRequestDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    List<CityDTO> convertToCityDtoList(List<City> cityList);

    @Mapping(target = "userId", source = "user.id")
    CityDTO convertToCityDto(City city);

    @Mapping(target = "user.id", source = "userId")
    City convertToCity(CitySaveRequestDTO citySaveRequestDTO);
}
