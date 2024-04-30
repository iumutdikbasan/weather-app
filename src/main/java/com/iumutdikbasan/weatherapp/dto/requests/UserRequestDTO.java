package com.iumutdikbasan.weatherapp.dto.requests;

import com.iumutdikbasan.weatherapp.annotations.UniqueUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotNull
        @Size(min = 4, max = 255)
        @UniqueUsername String username,

        @NotNull
        @Size(min = 8, max = 255) String password) {
}