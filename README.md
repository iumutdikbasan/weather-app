[Türkçe Açıklama için tıklayınız](#TR)
# EN
# Weather App
This project involves developing an application that provides weather forecasts using RESTful web services. The project allows users to query weather forecasts for a city and retrieves weather forecasts using the OpenWeatherMap API. In the backend, operations such as setting up a RESTful web service, managing the database, providing daily forecasts, user registration, utilizing the API, writing tests, creating documentation, and establishing logging mechanisms are performed. On the frontend, user interface and design are developed. Expectations from the project include ensuring code quality, structuring, writing tests, creating documentation, and ensuring the proper functioning of logging mechanisms.

### Prerequisites

**Run this command at your local** if you want to check frontend code. <a href="https://github.com/iumutdikbasan/weather-app-frontend" target="_blank">Click</a>
```
git clone https://github.com/iumutdikbasan/weather-app-frontend.git && cd weather-app-frontend && npm install && npm start
```

### Backend:
- A RESTful web service developed with Spring Boot. Provides weather forecast by taking city and other parameters.
### Daily Predictions: 
- Weather forecasts are provided in 3-hour intervals covering a 5-day period.
### User Registration: 
- By creating an account, users can save their cities and view weather forecasts for saved cities.

## Technologies Used in Backend

- Spring Boot
- Maven
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- Spring Cloud OpenFeign
- Spring Kafka (Logging)
- SLF4J (Logging)
- JSON Web Token (JWT)
- H2DB
- Lombok
- Mockito(Tests)
- SpringDoc OpenAPI (Documentation)
- Spring Kafka Test
- Spring Security Test

## Technologies Used in Frontend

- ReactJs
- Redux
- Axios


## Tests
Tests were written to ensure the quality and functionality of the project.
Testing is performed using Spring Boot Testing Framework.
You can use the following command to run the tests:
```
mvn test
```

### Includes
Includes database integration, DTOs, service classes, and other helper classes. These enable the recording, querying and management of weather forecasts by collaborating with the relevant controller classes.

## Frontend Images

![Home](https://github.com/iumutdikbasan/weather-app/assets/54438200/c2f3e14f-4cdb-4beb-b482-0fb06c18570a)
![singup](https://github.com/iumutdikbasan/weather-app/assets/54438200/4bee550f-541a-4044-bebd-99f47bf58c43)
![login](https://github.com/iumutdikbasan/weather-app/assets/54438200/6a38c019-b743-4964-bbe9-b4c5e8e8f091)
![havadurumu](https://github.com/iumutdikbasan/weather-app/assets/54438200/cfc0c2b5-3de4-4b5e-9802-d9eaf62ddd44)
![Screenshot 2024-05-01 111518](https://github.com/iumutdikbasan/weather-app/assets/54438200/14a5bb5d-67fd-4467-bfca-28d42a93a667)
![Screenshot 2024-05-01 112049](https://github.com/iumutdikbasan/weather-app/assets/54438200/c7d7ee93-eb49-4a3b-a0b8-947cb26eec79)


The application automatically provides API documentation with the SpringDoc OpenAPI tool. Once the application runs successfully, you can access the API documentation via the following URL:
```
http://localhost:8080/swagger-ui.html
```

## APIs
![apis](https://github.com/iumutdikbasan/weather-app/assets/54438200/0ed1e13b-7046-43a6-b7f3-03d43fdb3ed0)
![apis2](https://github.com/iumutdikbasan/weather-app/assets/54438200/968223e8-333a-4dcc-b7fd-c480d8ecca35)


## Conclusion
In this project, a weather application was developed using Spring Boot and other technologies.
The project enables the creation of a RESTful web service, database integration, API usage and log tracking.
Additionally, the project scope includes tests and API documentation.

# TR


# Hava Durumu Uygulaması

Bu proje, RESTful web servislerini kullanarak hava durumu tahminlerini sağlayan bir uygulamanın geliştirilmesini içerir. Proje, kullanıcıların bir şehirdeki hava durumu tahminlerini sorgulama imkanı sunar ve OpenWeatherMap API'si kullanılarak hava durumu tahminleri alır. Backend kısmında, RESTful web servisi, veritabanı yönetimi, günlük tahminlerin sağlanması, kullanıcı kaydı, API kullanımı, testlerin yazılması, dokümantasyonun oluşturulması ve loglama mekanizmasının kurulması gibi işlemler gerçekleştirilir. Frontend kısmında ise, kullanıcı arayüzü ve arayüz tasarımı geliştirilir. Projeden beklenenler arasında, kodun kalitesi, yapılandırılması, testlerin yazılması, dokümantasyonun oluşturulması ve loglama mekanizmasının doğru çalışması yer alır.

### Prerequisites

**Başlamadan önce bu komutu localinizde çalıştırın** eğer frontend kodu kontrol etmek istiyorsanız. <a href="https://github.com/iumutdikbasan/weather-app-frontend" target="_blank">Tıklayınız</a>
```
git clone https://github.com/iumutdikbasan/weather-app-frontend.git && cd weather-app-frontend && npm install && npm start
```

### Backend:
- Spring Boot ile geliştirilmiş bir RESTful web servisi. Şehir ve diğer parametreleri alarak hava durumu tahmini sağlar.
### Günlük Tahminler: 
- Hava durumu tahminleri, 5 günlük süreyi kapsayan 3 saatlik aralıklarla sağlanır.
### Kullanıcı Kaydı: 
- Kullanıcılar, bir hesap oluşturarak şehirlerini kaydedebilir ve kaydedilen şehirler için hava durumu tahminlerini görüntüleyebilir.
  
## Backend Kısmında Kullanılan Teknolojiler
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
- H2DB
- Lombok
- MapStruct
- Mockito(Test işlemleri)
- SpringDoc OpenAPI (Dokümantasyon)
- Spring Kafka Test
- Spring Security Test

 ## Frontend kısmında kullanılan teknolojiler

- ReactJs
- Redux
- Axios

## Hata Yakalama
Projede bir hata yakalama mekanizması kurulmuştur. 

## Testler
Projenin kalitesini ve işlevselliğini sağlamak için testler yazılmıştır.
Testler, Spring Boot Testing Framework kullanılarak gerçekleştirilir.
Testleri çalıştırmak için aşağıdaki komutu kullanabilirsiniz:
```
mvn test
```

### Diğer Bileşenler
Veritabanı entegrasyonu, DTO'lar, servis sınıfları ve diğer yardımcı sınıflar yer alır. Bunlar, ilgili controller sınıflarıyla işbirliği yaparak hava durumu tahminlerinin kaydedilmesi, sorgulanması ve yönetilmesini sağlar.

## Frontend Images

![Home](https://github.com/iumutdikbasan/weather-app/assets/54438200/c2f3e14f-4cdb-4beb-b482-0fb06c18570a)
![singup](https://github.com/iumutdikbasan/weather-app/assets/54438200/4bee550f-541a-4044-bebd-99f47bf58c43)
![login](https://github.com/iumutdikbasan/weather-app/assets/54438200/6a38c019-b743-4964-bbe9-b4c5e8e8f091)
![havadurumu](https://github.com/iumutdikbasan/weather-app/assets/54438200/cfc0c2b5-3de4-4b5e-9802-d9eaf62ddd44)
![Screenshot 2024-05-01 111518](https://github.com/iumutdikbasan/weather-app/assets/54438200/14a5bb5d-67fd-4467-bfca-28d42a93a667)
![Screenshot 2024-05-01 112049](https://github.com/iumutdikbasan/weather-app/assets/54438200/c7d7ee93-eb49-4a3b-a0b8-947cb26eec79)

Uygulama, SpringDoc OpenAPI aracıyla otomatik olarak API dokümantasyonu sağlar. Uygulama başarıyla çalıştırıldığında, API belgelerine aşağıdaki URL üzerinden erişebilirsiniz:
```
http://localhost:8080/swagger-ui.html
```
Bu sayfada, mevcut API'lerin listesini, parametrelerini, yanıtlarını ve diğer ayrıntıları bulabilirsiniz.


## APIs
![apis](https://github.com/iumutdikbasan/weather-app/assets/54438200/0ed1e13b-7046-43a6-b7f3-03d43fdb3ed0)
![apis2](https://github.com/iumutdikbasan/weather-app/assets/54438200/968223e8-333a-4dcc-b7fd-c480d8ecca35)

## Sonuç
Bu projede, Spring Boot ve diğer teknolojileri kullanarak bir hava durumu uygulaması geliştirilmiştir.
Proje, RESTful bir web servisi oluşturulması, veritabanı entegrasyonunu gerçekleştirilmesi, API kullanımını sağlamanması ve logların takibinin yapılmasını sağlar.
Ayrıca, proje kapsamında testler ve API dokümantasyonu da yer almaktadır.
