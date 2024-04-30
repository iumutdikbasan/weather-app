package com.iumutdikbasan.weatherapp.dto.responses;


import com.iumutdikbasan.weatherapp.general.BaseAdditionalFields;

public record CityResponseDTO(
        Long id,
        String cityName,

        String temp,

        String infoDate,

        String icon,
        BaseAdditionalFields baseAdditionalFields
) {

}