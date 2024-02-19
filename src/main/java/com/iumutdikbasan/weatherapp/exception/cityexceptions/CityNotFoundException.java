package com.iumutdikbasan.weatherapp.exception.cityexceptions;

import com.iumutdikbasan.weatherapp.dto.city.errorresponse.CityExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/*
    Uygulama üzerinden istemci tarafına iletilecek olan
    exceptionları yöneterek global exception handling yapmamıza olanak sağlar.
    Bu anotasyonu kullarak uygulamamızın herhangi bir noktasından fırlatılan
    ve Controller’lar üzerinden istemciye iletilen hataların tiplerini dönüştürebilir,
    farklı HTTP statü kodlarını kullanarak özelleştirebilir
    veya ihtiyaçlar doğrultusunda değiştirdiğimiz cevapları istemci tarafına iletebiliriz.
*/
@RestControllerAdvice
public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String message) {
        super(message);
    }
}
