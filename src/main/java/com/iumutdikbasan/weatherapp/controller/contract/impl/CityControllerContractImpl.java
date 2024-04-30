package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.controller.contract.CityControllerContract;
import com.iumutdikbasan.weatherapp.dto.requests.CityRequestDTO;
import com.iumutdikbasan.weatherapp.dto.responses.CityResponseDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.mapper.CityMapper;
import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.service.concretes.CityServiceImpl;
import com.iumutdikbasan.weatherapp.service.concretes.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CityControllerContractImpl implements CityControllerContract {

    private final CityMapper cityMapper;

    private final CityServiceImpl cityServiceImpl;

    private final KafkaService kafkaService;

    private final UserServiceImpl userServiceImpl;

    @Override
    public CityResponseDTO saveCity(CityRequestDTO cityRequestDTO) {

        String record = String.format("[%s:%d] saveCity method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        Optional<User> user = userServiceImpl.findById(cityRequestDTO.userId());
        if(!user.isPresent()){
            throw new MyException("Kayıt Başarısız");
        }else {
            City city = cityMapper.toCity(cityRequestDTO);
            city.setUser(user.get());
            City savedCity = cityServiceImpl.save(city);
            CityResponseDTO cityResponseDTO = cityMapper.toCityResponseDTO(savedCity);
            record = String.format("[%s:%d] City saved: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTO);
            kafkaService.sendMessage(record,"info");
            return cityResponseDTO;
        }

    }

    @Override
    public List<CityResponseDTO> getAllCities() {
        String record = String.format("[%s:%d] getAllCities method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        List<City> cities = cityServiceImpl.findAll();
        List<CityResponseDTO> cityResponseDTOS = cities.stream().map(cityMapper::toCityResponseDTO).toList();

        if(cityResponseDTOS.isEmpty()){
            record = String.format("[%s:%d] No cities found ", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Listelenecek şehir bulunamadı");
        }

        record = String.format("[%s:%d] Retrieved %d cities", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTOS.size());
        kafkaService.sendMessage(record,"info");
        return cityResponseDTOS;
    }

    @Override
    public CityResponseDTO getCityById(Long id) {

        String record = String.format("[%s:%d] getCityById method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        Optional<City> city = cityServiceImpl.findById(id);
        if(!city.isPresent()){
            record = String.format("[%s:%d] City not found with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Şehir bulunamadı");
        }else {
            CityResponseDTO cityResponseDTO = cityMapper.toCityResponseDTO(city.get());
            record = String.format("[%s:%d] City found: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTO);
            kafkaService.sendMessage(record,"info");
            return cityResponseDTO;
        }


    }

    @Override
    public List<CityResponseDTO> getCitiesByUserId(Long id) {

        String record = String.format("[%s:%d] getCitiesByUserId method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        Optional<User> user = userServiceImpl.findById(id);
        if(!user.isPresent()){
            record = String.format("[%s:%d] User not found with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Kullanıcı bulunamadı");
        }else {
            List<City> cities = cityServiceImpl.findByUserId(id);
            List<CityResponseDTO> cityResponseDTOS = cities.stream().map(cityMapper::toCityResponseDTO).toList();
            record = String.format("[%s:%d] Retrieved %d cities", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTOS.size());
            kafkaService.sendMessage(record,"info");
            return cityResponseDTOS;
        }
    }

    @Override
    public CityResponseDTO deleteCityById(Long id) {

        String record = String.format("[%s:%d] deleteCityById method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        Optional<City> city = cityServiceImpl.findById(id);
        if(!city.isPresent()){
            record = String.format("[%s:%d] City not found with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Şehir bulunamadı");
        }else {
            cityServiceImpl.delete(id);
            CityResponseDTO cityResponseDTO = cityMapper.toCityResponseDTO(city.get());
            record = String.format("[%s:%d] City deleted with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record,"info");
            return cityResponseDTO;
        }
    }

    @Override
    public List<CityResponseDTO> getAllCitiesOrderByCityName() {

        String record = String.format("[%s:%d] getAllCitiesOrderByCityName method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        List<City> cities = cityServiceImpl.getAllByOrderByCityNameAsc();
        List<CityResponseDTO> cityResponseDTOS = cities.stream().map(cityMapper::toCityResponseDTO).toList();

        if(cityResponseDTOS.isEmpty()){
            record = String.format("[%s:%d] No cities found ", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Listelenecek şehir bulunamadı");
        }

        record = String.format("[%s:%d] Cities found: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTOS);
        kafkaService.sendMessage(record,"info");
        return cityResponseDTOS;
    }

    @Override
    public List<CityResponseDTO> getCitiesByCityNameContains(String cityName) {

        String record = String.format("[%s:%d] getCitiesByCityNameContains method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        List<City> cities = cityServiceImpl.getByCityNameContaining(cityName);
        List<CityResponseDTO> cityResponseDTOS = cities.stream().map(cityMapper::toCityResponseDTO).toList();

        if(cityResponseDTOS.isEmpty()){
            record = String.format("[%s:%d] No cities found ", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Listelenecek şehir bulunamadı");
        }

        record = String.format("[%s:%d] Cities found: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTOS);
        kafkaService.sendMessage(record,"info");
        return cityResponseDTOS;
    }

    @Override
    public List<CityResponseDTO> getCitiesByCityNameStartsWith(String cityName) {

        String record = String.format("[%s:%d] getCitiesByCityNameStartsWith method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        List<City> cities = cityServiceImpl.getByCityNameStartsWith(cityName);
        List<CityResponseDTO> cityResponseDTOS = cities.stream().map(cityMapper::toCityResponseDTO).toList();

        if(cityResponseDTOS.isEmpty()){
            record = String.format("[%s:%d] No cities found ", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Listelenecek şehir bulunamadı");
        }

        record = String.format("[%s:%d] Cities found: %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTOS);
        kafkaService.sendMessage(record,"info");
        return cityResponseDTOS;
    }

    @Override
    public List<CityResponseDTO> getCitiesByPage(int i, int size) {
        String record = String.format("[%s:%d] getCitiesByPage method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        if(i<0 || size<0){
            record = String.format("[%s:%d] Page number or size cannot be negative ", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Sayfa numarası veya sayfa boyutu negatif olamaz");
        }

        List<City> cities = cityServiceImpl.getCitiesPage(i,size);
        List<CityResponseDTO> cityResponseDTOS = cities.stream().map(cityMapper::toCityResponseDTO).toList();

        record = String.format("[%s:%d] Retrieved %d cities for page number %d and page size %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), cityResponseDTOS.size(), i, size);
        kafkaService.sendMessage(record,"info");
        return cityResponseDTOS;
    }

    @Override
    public CityResponseDTO updateCity(Long id, CityRequestDTO cityRequestDTO) {

        String record = String.format("[%s:%d] updateCity method called", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
        kafkaService.sendMessage(record,"debug");

        Optional<City> city = cityServiceImpl.findById(id);
        if(!city.isPresent()){
            record = String.format("[%s:%d] City not found with id: %d", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), id);
            kafkaService.sendMessage(record,"warn");
            throw new MyException("Şehir bulunamadı");
        }else {
            Optional<User> user = userServiceImpl.findById(cityRequestDTO.userId());
            City city1 = cityMapper.toCity(cityRequestDTO);
            city1.setUser(user.get());
            cityServiceImpl.delete(id);
            cityServiceImpl.save(city1);
            CityResponseDTO cityResponseDTO = cityMapper.toCityResponseDTO(city1);


            record = String.format("[%s:%d] City updated ", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            kafkaService.sendMessage(record,"info");
            return cityResponseDTO;
        }
    }
}