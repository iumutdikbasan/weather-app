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
//    @Mapping(target = "baseAdditionalFields", ignore = true)
//    @Mapping(target = "id", ignore = true)
    CityDTO convertToCityDto(City city);

    @Mapping(target = "user.id", source = "userId")
//    @Mapping(target = "baseAdditionalFields", ignore = true)
//    @Mapping(target = "id", ignore = true)
    City convertToCity(CitySaveRequestDTO citySaveRequestDTO);
}
