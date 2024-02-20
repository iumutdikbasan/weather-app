[English description](#EN)
[Türkçe Açıklama](#TR)


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
### Veritabanı:
- Kullanıcıların aradığı şehirleri ve tahminleri kaydetmek için bir veritabanı sistemi kullanılır.
### Günlük Tahminler: 
- Hava durumu tahminleri, 5 günlük süreyi kapsayan 3 saatlik aralıklarla sağlanır.
### Kullanıcı Kaydı: 
- Kullanıcılar, bir hesap oluşturarak şehirlerini kaydedebilir ve kaydedilen şehirler için hava durumu tahminlerini görüntüleyebilir.
### API Kullanımı:
- OpenWeatherMap API'si, hava durumu tahminlerini sağlamak için kullanılır.
### Testler: 
- Kod kalitesini ve performansı sağlamak için otomatik testler yazılır.
### Dokümantasyon: 
- Swagger kullanılarak RESTful servisin dokümantasyonu sağlanır.
### Loglama: 
- Uygulamanın hata ayıklanmasına yardımcı olmak için loglama mekanizması kurulur.


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
- Mockito
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
