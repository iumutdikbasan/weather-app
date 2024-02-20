[Türkçe Açıklama için tıklayınız](#TR)
# EN
# Weather App
This project is a weather application developed using Spring Boot.
The application provides weather forecasts using a RESTful web service.
It allows users to query the weather forecast in a city. Weather forecasts are provided in 3-hour intervals covering a 5-day period.
The app retrieves weather forecasts using the OpenWeatherMap API.

### Backend:
- A RESTful web service developed with Spring Boot. Provides weather forecast by taking city and other parameters.
### Daily Predictions: 
- Weather forecasts are provided in 3-hour intervals covering a 5-day period.
### User Registration: 
- By creating an account, users can save their cities and view weather forecasts for saved cities.

## Used technologies

- Spring Boot
- Maven
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- Spring Cloud OpenFeign
- Spring Kafka (Logging)
- SLF4J (Loglama)
- JSON Web Token (JWT)
- PostgreSQL
- Lombok
- MapStruct
- Mockito(Tests)
- SpringDoc OpenAPI (Documentation)
- Spring Kafka Test
- Spring Security Test

## APIs
![image](https://github.com/iumutdikbasan/weather-app/assets/54438200/e840b39b-152b-4489-b60b-ee1a7fc7f5b8)


The application automatically provides API documentation with the SpringDoc OpenAPI tool. Once the application runs successfully, you can access the API documentation via the following URL:
```
http://localhost:8080/swagger-ui.html
```

## Tests
Tests were written to ensure the quality and functionality of the project.
Testing is performed using Spring Boot Testing Framework.
You can use the following command to run the tests:
```
mvn test
```

## Project Details
This project forms the backend part of a weather application.
The application allows users to save and view weather forecasts for cities.
Below you can find more detailed descriptions of the different components of the project.

### CityController
CityController is a controller class that processes HTTP requests related to cities. The relevant endpoints are:

- GET /api/v1/cities: Returns the cities saved by the user.
- POST /api/v1/cities: Allows the user to save a new city.
- DELETE /api/v1/cities/{id}: Deletes cities by id.
### UserController
UserController is a controller class that processes HTTP requests related to users. The relevant endpoints are:

- GET /api/v1/users: Get all users
- GET /api/v1/users/user/cities/{unit}: It brings weather data of the cities saved by the user.
### AuthenticationController
AuthenticationController is a controller class that processes HTTP requests related to user authentication and registration. The relevant endpoints are:

- POST /api/v1/auth/register: Allows the user to register.
- POST /api/v1/auth/authenticate: Provides user authentication.
- POST /api/v1/auth/refresh-token: Performs the user's token renewal process.
### WeatherController
WeatherController uses the OpenWeatherMap API to get weather forecasts.
- GET /api/v1/weather/data: Brings weather forecasts.
### Includes
Includes database integration, DTOs, service classes, and other helper classes. These enable the recording, querying and management of weather forecasts by collaborating with the relevant controller classes.

## Conclusion
In this project, a weather application was developed using Spring Boot and other technologies.
The project enables the creation of a RESTful web service, database integration, API usage and log tracking.
Additionally, the project scope includes tests and API documentation.
# TR


# Hava Durumu Uygulaması
Bu proje, Spring Boot kullanılarak geliştirilen bir hava durumu uygulamasıdır. 
Uygulama, RESTful bir web servisi kullanarak hava durumu tahminleri sağlar. 
Kullanıcılara bir şehirdeki hava durumu tahminlerini sorgulama imkanı sunar. Hava durumu tahminleri, 5 günlük süreyi kapsayan 3 saatlik aralıklarla sağlanır.
Uygulama, OpenWeatherMap API'sini kullanarak hava durumu tahminlerini alır.

## Proje İçeriği
Proje, aşağıdaki bileşenleri içerir:

### Backend:
- Spring Boot ile geliştirilmiş bir RESTful web servisi. Şehir ve diğer parametreleri alarak hava durumu tahmini sağlar.
### Günlük Tahminler: 
- Hava durumu tahminleri, 5 günlük süreyi kapsayan 3 saatlik aralıklarla sağlanır.
### Kullanıcı Kaydı: 
- Kullanıcılar, bir hesap oluşturarak şehirlerini kaydedebilir ve kaydedilen şehirler için hava durumu tahminlerini görüntüleyebilir.
## Kullanılan Teknolojiler
Projenin geliştirilmesinde aşağıdaki teknolojiler kullanılmıştır:

- Spring Boot
- Maven
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- Spring Cloud OpenFeign
- Spring Kafka (Loglama)
- SLF4J (Loglama)
- JSON Web Token (JWT)
- PostgreSQL
- Lombok
- MapStruct
- Mockito(Test işlemleri)
- SpringDoc OpenAPI (Dokümantasyon)
- Spring Kafka Test
- Spring Security Test

## Hata Yakalama
Projede bir hata yakalama mekanizması kurulmuştur. 

## API Dokümantasyonu
![image](https://github.com/iumutdikbasan/weather-app/assets/54438200/e840b39b-152b-4489-b60b-ee1a7fc7f5b8)


Uygulama, SpringDoc OpenAPI aracıyla otomatik olarak API dokümantasyonu sağlar. Uygulama başarıyla çalıştırıldığında, API belgelerine aşağıdaki URL üzerinden erişebilirsiniz:
```
http://localhost:8080/swagger-ui.html
```
Bu sayfada, mevcut API'lerin listesini, parametrelerini, yanıtlarını ve diğer ayrıntıları bulabilirsiniz.

## Testler
Projenin kalitesini ve işlevselliğini sağlamak için testler yazılmıştır.
Testler, Spring Boot Testing Framework kullanılarak gerçekleştirilir.
Testleri çalıştırmak için aşağıdaki komutu kullanabilirsiniz:
```
mvn test
```

## Proje Ayrıntıları
Bu proje, bir hava durumu uygulamasının backend kısmını oluşturur.
Uygulama, kullanıcılara şehirlerin hava durumu tahminlerini kaydetme ve görüntüleme imkanı sağlar.
Aşağıda, projenin farklı bileşenlerinin daha detaylı açıklamalarını bulabilirsiniz.

### CityController
CityController, şehirlerle ilgili HTTP isteklerini işleyen bir controller sınıfıdır. İlgili endpoint'ler şunlardır:

- GET /api/v1/cities: Kullanıcının kaydettiği şehirleri getirir.
- POST /api/v1/cities: Kullanıcının yeni bir şehir kaydetmesini sağlar.
- DELETE /api/v1/cities/{id}: Belirli bir şehiri siler.
### UserController
UserController, kullanıcılarla ilgili HTTP isteklerini işleyen bir controller sınıfıdır. İlgili endpoint'ler şunlardır:

- GET /api/v1/users: Tüm kullanıcıları getirir.
- GET /api/v1/users/user/cities/{unit}: Kullanıcının kaydettiği şehirlerin hava durumu verilerini getirir.
### AuthenticationController
AuthenticationController, kullanıcı kimlik doğrulama ve kayıt işlemleriyle ilgili HTTP isteklerini işleyen bir controller sınıfıdır. İlgili endpoint'ler şunlardır:

- POST /api/v1/auth/register: Kullanıcının kaydolmasını sağlar.
- POST /api/v1/auth/authenticate: Kullanıcının kimlik doğrulamasını sağlar.
- POST /api/v1/auth/refresh-token: Kullanıcının token yenileme işlemini gerçekleştirir.
### WeatherController
WeatherController, hava durumu tahminlerini almak için OpenWeatherMap API'sini kullanır. 
- GET /api/v1/weather/data: Hava durumu tahminlerini döndürür.
### Diğer Bileşenler
Veritabanı entegrasyonu, DTO'lar, servis sınıfları ve diğer yardımcı sınıflar yer alır. Bunlar, ilgili controller sınıflarıyla işbirliği yaparak hava durumu tahminlerinin kaydedilmesi, sorgulanması ve yönetilmesini sağlar.

## Sonuç
Bu projede, Spring Boot ve diğer teknolojileri kullanarak bir hava durumu uygulaması geliştirilmiştir.
Proje, RESTful bir web servisi oluşturulması, veritabanı entegrasyonunu gerçekleştirilmesi, API kullanımını sağlamanması ve logların takibinin yapılmasını sağlar.
Ayrıca, proje kapsamında testler ve API dokümantasyonu da yer almaktadır.
