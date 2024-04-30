package com.iumutdikbasan.weatherapp.dto.responses;


import com.iumutdikbasan.weatherapp.general.BaseAdditionalFields;

import java.util.List;

public record UserResponseDTO(
        Long id,
        String username,
        List<CityResponseDTO> cities,
        BaseAdditionalFields baseAdditionalFields
) {

}