package com.iumutdikbasan.weatherapp.dto.city.errorresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityExceptionResponse {
    private LocalDateTime timeStamp;
    private String message;
    private int statusCode;
}
