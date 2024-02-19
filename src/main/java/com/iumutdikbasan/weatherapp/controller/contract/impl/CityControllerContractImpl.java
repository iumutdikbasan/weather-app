package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.controller.contract.CityControllerContract;
import com.iumutdikbasan.weatherapp.dto.city.request.CitySaveRequestDTO;
import com.iumutdikbasan.weatherapp.dto.city.response.CityResponseDTO;
import com.iumutdikbasan.weatherapp.dto.user.response.UserResponseDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.errormessages.CityErrorMessage;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotCreatedException;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotDeletedException;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotFoundException;
import com.iumutdikbasan.weatherapp.kafka.service.KafkaService;
import com.iumutdikbasan.weatherapp.mapper.CityMapper;
import com.iumutdikbasan.weatherapp.mapper.UserMapper;
import com.iumutdikbasan.weatherapp.security.user.User;
import com.iumutdikbasan.weatherapp.service.CityEntityService;
import com.iumutdikbasan.weatherapp.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityControllerContractImpl implements CityControllerContract {

    private final CityEntityService service;
    private final CityMapper mapper;
    private final UserEntityService userEntityService;

    @Override
    public List<CityResponseDTO> findByUserId(Long userId) {
        List<City> cityList = service.findByUserId(userId);
        if (!cityList.isEmpty()) {
            return mapper.convertToCityDtoList(cityList);
        } else {
            throw new CityNotFoundException(CityErrorMessage.CITY_NOT_FOUND_WITH_ID.getMessage() + userId);
        }
    }

    @Override
    public List<CityResponseDTO> findCityByUserId() {
        User user = userEntityService.extractUser();

        List<City> cityList = service.findByUserId(user.getId());
        if (!cityList.isEmpty()) {
            return mapper.convertToCityDtoList(cityList);
        } else {
            throw new CityNotFoundException(CityErrorMessage.CITY_NOT_FOUND_WITH_ID.getMessage() + user.getId());
        }
    }

    @Override
    public CityResponseDTO save(CitySaveRequestDTO citySaveRequestDTO) {
        City city = mapper.convertToCity(citySaveRequestDTO);
        User user = userEntityService.extractUser();
        try {
            city.setUser(user);
            service.save(city);
            return mapper.convertToCityDto(city);
        }catch (Exception e){
            throw  new CityNotCreatedException(CityErrorMessage.CITY_NOT_CREATED_WITH_USER_ID.getMessage() + user.getId());
        }

    }

    @Override
    public void delete(Long id) {
        Optional<City> city = service.findById(id);
        if(city.isPresent()) {
            service.delete(id);
        }else{
            throw new CityNotDeletedException(CityErrorMessage.CITY_NOT_DELETED_WITH_ID.getMessage() + id);
        }


    }
}