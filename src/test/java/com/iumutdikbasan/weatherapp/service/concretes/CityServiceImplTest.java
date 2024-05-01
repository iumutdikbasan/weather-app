package com.iumutdikbasan.weatherapp.service.concretes;

import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.entity.User;
import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.general.BaseAdditionalFields;
import com.iumutdikbasan.weatherapp.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @Captor
    private ArgumentCaptor<City> cityCaptor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCity_WithNullBaseAdditionalFields_ShouldCreateNewBaseAdditionalFields() {
        City city = new City();
        city.setUser(new User(1L,"username","password",null));
        city.setId(null);

        cityService.save(city);

        verify(cityRepository).save(cityCaptor.capture());
        City capturedCity = cityCaptor.getValue();

        Assertions.assertNotNull(capturedCity.getBaseAdditionalFields());
        Assertions.assertNotNull(capturedCity.getBaseAdditionalFields().getCreatedDate());
        Assertions.assertEquals("username", capturedCity.getBaseAdditionalFields().getCreatedBy());
        Assertions.assertNotNull(capturedCity.getBaseAdditionalFields().getUpdatedDate());
        Assertions.assertEquals("username", capturedCity.getBaseAdditionalFields().getUpdatedBy());
    }

    @Test
    public void testSaveCity_WithExistingBaseAdditionalFields_ShouldUpdateBaseAdditionalFields() {
        City city = new City();
        city.setUser(new User(1L,"username","password",null));
        city.setId(1L);
        BaseAdditionalFields baseAdditionalFields = new BaseAdditionalFields();
        baseAdditionalFields.setCreatedDate(LocalDateTime.of(2021, 1, 1, 0, 0));
        baseAdditionalFields.setCreatedBy("previousUser");
        city.setBaseAdditionalFields(baseAdditionalFields);

        cityService.save(city);

        verify(cityRepository).save(cityCaptor.capture());
        City capturedCity = cityCaptor.getValue();

        Assertions.assertEquals(baseAdditionalFields, capturedCity.getBaseAdditionalFields());
        Assertions.assertEquals("username", capturedCity.getBaseAdditionalFields().getUpdatedBy());
        Assertions.assertNotNull(capturedCity.getBaseAdditionalFields().getUpdatedDate());
    }

    @Test
    public void testFindByUserId_WithExistingCities_ShouldReturnCities() {
        Long userId = 1L;
        List<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());

        when(cityRepository.findByUserId(userId)).thenReturn(cities);

        List<City> result = cityService.findByUserId(userId);

        Assertions.assertEquals(cities, result);
    }

    @Test
    public void testFindByUserId_WithNoCities_ShouldThrowException() {
        Long userId = 1L;

        when(cityRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(MyException.class, () -> cityService.findByUserId(userId));
    }

    @Test
    public void testGetAllByOrderByCityNameAsc_WithExistingCities_ShouldReturnCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());

        when(cityRepository.getAllByOrderByCityNameAsc()).thenReturn(cities);

        List<City> result = cityService.getAllByOrderByCityNameAsc();

        Assertions.assertEquals(cities, result);
    }

    @Test
    public void testGetAllByOrderByCityNameAsc_WithNoCities_ShouldThrowException() {
        when(cityRepository.getAllByOrderByCityNameAsc()).thenReturn(new ArrayList<>());

        Assertions.assertThrows(MyException.class, () -> cityService.getAllByOrderByCityNameAsc());
    }

    @Test
    public void testGetByCityNameContaining_WithExistingCities_ShouldReturnCities() {
        String cityName = "city";

        List<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());

        when(cityRepository.getByCityNameContaining(cityName)).thenReturn(cities);

        List<City> result = cityService.getByCityNameContaining(cityName);

        Assertions.assertEquals(cities, result);
    }

    @Test
    public void testGetByCityNameContaining_WithNoCities_ShouldThrowException() {
        String cityName = "city";

        when(cityRepository.getByCityNameContaining(cityName)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(MyException.class, () -> cityService.getByCityNameContaining(cityName));
    }

    @Test
    public void testGetByCityNameStartsWith_WithExistingCities_ShouldReturnCities() {
        String cityName = "city";

        List<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());

        when(cityRepository.getByCityNameStartsWith(cityName)).thenReturn(cities);

        List<City> result = cityService.getByCityNameStartsWith(cityName);

        Assertions.assertEquals(cities, result);
    }

    @Test
    public void testGetByCityNameStartsWith_WithNoCities_ShouldThrowException() {
        String cityName = "city";

        when(cityRepository.getByCityNameStartsWith(cityName)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(MyException.class, () -> cityService.getByCityNameStartsWith(cityName));
    }

    @Test
    public void testGetCitiesPage_WithExistingCities_ShouldReturnCities() {
        int pageNumber = 1;
        int pageSize = 10;

        List<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());

        Page<City> cityPage = new PageImpl<>(cities);
        when(cityRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(cityPage);

        List<City> result = cityService.getCitiesPage(pageNumber, pageSize);

        Assertions.assertEquals(cities, result);
    }

    @Test
    public void testGetCitiesPage_WithNoCities_ShouldThrowException() {
        int pageNumber = 1;
        int pageSize = 10;

        Page<City> emptyCityPage = new PageImpl<>(new ArrayList<>());
        when(cityRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(emptyCityPage);

        Assertions.assertThrows(MyException.class, () -> cityService.getCitiesPage(pageNumber, pageSize));
    }
}