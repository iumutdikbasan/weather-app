package com.iumutdikbasan.weatherapp.exception.jwtexceptions.exception;

import com.iumutdikbasan.weatherapp.dto.jwt.errorresponse.JWTExceptionResponse;
import com.iumutdikbasan.weatherapp.exception.jwtexceptions.exceptiohandler.JWTNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class JwtExceptionsHandler {
    @ExceptionHandler(JWTNotCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JWTExceptionResponse handleJwtNotCreatedException(JWTNotCreatedException jwtNotCreatedException){
        return errorResponseSetter(LocalDateTime.now(),jwtNotCreatedException.getMessage(),HttpStatus.BAD_REQUEST.value());
    }
    private JWTExceptionResponse errorResponseSetter(LocalDateTime localDateTime, String message, int statusCode){
        return JWTExceptionResponse.builder()
                .timeStamp(localDateTime)
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}
