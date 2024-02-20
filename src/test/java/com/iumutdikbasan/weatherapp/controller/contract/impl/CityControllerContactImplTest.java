package com.iumutdikbasan.weatherapp.controller.contract.impl;

import com.iumutdikbasan.weatherapp.dto.city.request.CitySaveRequestDTO;
import com.iumutdikbasan.weatherapp.dto.city.response.CityResponseDTO;
import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotCreatedException;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotDeletedException;
import com.iumutdikbasan.weatherapp.exception.cityexceptions.CityNotFoundException;
import com.iumutdikbasan.weatherapp.kafka.service.KafkaService;
import com.iumutdikbasan.weatherapp.mapper.CityMapper;
import com.iumutdikbasan.weatherapp.security.user.User;
import com.iumutdikbasan.weatherapp.service.CityEntityService;
import com.iumutdikbasan.weatherapp.service.UserEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CityControllerContactImplTest {
    @Mock
    private CityEntityService cityEntityService;

    @Mock
    private CityMapper cityMapper;

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private KafkaService kafkaService;

    @InjectMocks
    private CityControllerContractImpl cityControllerContact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //TODO: Testler iyi olmadı düzeltmeyi unutma !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    void testFindCityByUserId_WhenCityExists() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        City city = new City();
        city.setName("Istanbul");

        List<City> cityList = new ArrayList<>();
        cityList.add(city);

        Mockito.when(userEntityService.extractUser()).thenReturn(user);
        Mockito.when(cityEntityService.findCitiesByUserId(userId)).thenReturn(cityList);
        Mockito.when(cityMapper.convertToCityDtoList(Mockito.anyList())).thenReturn(Arrays.asList(new CityResponseDTO(1L, "Istanbul", 1L)));

        List<CityResponseDTO> result = cityControllerContact.findCityByUserId();

        Assertions.assertEquals(1, result.size());
        Mockito.verify(kafkaService).sendMessageInfo(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void testFindCityByUserId_WhenCityDoesNotExist() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Mockito.when(userEntityService.extractUser()).thenReturn(user);
        Mockito.when(cityEntityService.findCitiesByUserId(userId)).thenReturn(new ArrayList<>());
        Mockito.doNothing().when(kafkaService).sendMessageError(Mockito.anyString(), Mockito.anyString());

        Assertions.assertThrows(CityNotFoundException.class, () -> cityControllerContact.findCityByUserId());

        Mockito.verify(kafkaService).sendMessageError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void testSave_WhenCityIsSaved() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        CitySaveRequestDTO requestDTO = new CitySaveRequestDTO("Istanbul");


        City city = new City();
        city.setName(requestDTO.name());
        city.setUser(user);

        Mockito.when(userEntityService.extractUser()).thenReturn(user);
        Mockito.when(cityMapper.convertToCity(requestDTO)).thenReturn(city);
        Mockito.when(cityEntityService.save(city)).thenReturn(city);
        Mockito.when(cityMapper.convertToCityDto(city)).thenReturn(new CityResponseDTO(1L, "Istanbul", 1L));

        CityResponseDTO result = cityControllerContact.save(requestDTO);

        Assertions.assertNotNull(result);
        Mockito.verify(kafkaService).sendMessageInfo(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void testSave_WhenCityIsNotSaved() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        CitySaveRequestDTO requestDTO = new CitySaveRequestDTO("Istanbul");

        City city = new City();
        city.setName(requestDTO.name());
        city.setUser(user);

        Mockito.when(userEntityService.extractUser()).thenReturn(user);
        Mockito.when(cityMapper.convertToCity(requestDTO)).thenReturn(city);
        Mockito.when(cityEntityService.save(city)).thenThrow(CityNotCreatedException.class);

        Assertions.assertThrows(CityNotCreatedException.class, () -> cityControllerContact.save(requestDTO));

        Mockito.verify(kafkaService).sendMessageError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void testDelete_WhenCityExists() {
        Long cityId = 1L;

        City city = new City();
        city.setId(cityId);
        city.setName("Istanbul");

        Optional<City> cityOptional = Optional.of(city);

        Mockito.when(cityEntityService.findById(cityId)).thenReturn(cityOptional);

        cityControllerContact.delete(cityId);

        Mockito.verify(kafkaService).sendMessageInfo(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(cityEntityService).delete(cityId);
    }

    @Test
    void testDelete_WhenCityDoesNotExist() {
        Long cityId = 1L;

        Mockito.when(cityEntityService.findById(cityId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CityNotDeletedException.class, () -> cityControllerContact.delete(cityId));

        Mockito.verify(kafkaService).sendMessageError(Mockito.anyString(), Mockito.anyString());
    }


}
