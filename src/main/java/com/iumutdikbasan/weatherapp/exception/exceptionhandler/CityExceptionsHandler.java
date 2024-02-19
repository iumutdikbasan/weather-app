package com.iumutdikbasan.weatherapp.exception.exceptionhandler;

import com.iumutdikbasan.weatherapp.dto.city.errorresponse.CityExceptionResponse;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotCreatedException;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotDeletedException;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CityExceptionsHandler {

    @ExceptionHandler(CityNotCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CityExceptionResponse handleCityNotCreatedException(CityNotDeletedException cityNotDeletedException){
        return errorResponseSetter(LocalDateTime.now(), cityNotDeletedException.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(CityNotDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CityExceptionResponse handleCityNotDeletedException(CityNotDeletedException cityNotDeletedException){
        return errorResponseSetter(LocalDateTime.now(), cityNotDeletedException.getMessage(),HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CityExceptionResponse handleCityNotFoundException(CityNotFoundException cityNotFoundException) {

        return errorResponseSetter(LocalDateTime.now(), cityNotFoundException.getMessage(), HttpStatus.NOT_FOUND.value());

    }




    private CityExceptionResponse errorResponseSetter(LocalDateTime localDateTime, String message, int statusCode) {
        return CityExceptionResponse.builder()
                .timeStamp(localDateTime)
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}
