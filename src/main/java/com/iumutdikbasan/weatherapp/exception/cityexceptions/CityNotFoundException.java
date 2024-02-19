package com.iumutdikbasan.weatherapp.exception.cityexceptions;


/*
    Uygulama üzerinden istemci tarafına iletilecek olan
    exceptionları yöneterek global exception handling yapmamıza olanak sağlar.
    Bu anotasyonu kullarak uygulamamızın herhangi bir noktasından fırlatılan
    ve Controller’lar üzerinden istemciye iletilen hataların tiplerini dönüştürebilir,
    farklı HTTP statü kodlarını kullanarak özelleştirebilir
    veya ihtiyaçlar doğrultusunda değiştirdiğimiz cevapları istemci tarafına iletebiliriz.
*/

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String message) {
        super(message);
    }
}
