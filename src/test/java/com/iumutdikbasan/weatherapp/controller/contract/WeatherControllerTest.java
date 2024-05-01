package com.iumutdikbasan.weatherapp.controller.contract;

import com.iumutdikbasan.weatherapp.controller.WeatherController;
import com.iumutdikbasan.weatherapp.dto.CurrentWeatherDTO;
import com.iumutdikbasan.weatherapp.dto.WeatherDTO;
import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.result.generalResult.DataResult;
import com.iumutdikbasan.weatherapp.service.concretes.WeatherService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @Mock
    private KafkaService kafkaService;

    @InjectMocks
    private WeatherController weatherController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFromController_Success() {
        // Arrange
        String cityName = "London";
        WeatherDTO weatherDTO = new WeatherDTO("EN",new ArrayList<>(),null); // Mock veya gerçek bir WeatherDTO nesnesi oluşturun

        when(weatherService.getWeather(eq(cityName))).thenReturn(weatherDTO);

        // Act
        ResponseEntity<DataResult<WeatherDTO>> response = weatherController.getFromController(cityName);

        // Assert
        verify(kafkaService, times(1)).sendMessage(anyString(), anyString());
        verify(weatherService, times(1)).getWeather(eq(cityName));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Listelendi", response.getBody().getMessage());
        assertEquals(weatherDTO, response.getBody().getData());
    }

    @Test
    public void testGetFromController_Failure() {
        // Arrange
        String cityName = "London";

        when(weatherService.getWeather(eq(cityName))).thenReturn(null);

        // Act
        try {
            weatherController.getFromController(cityName);
        } catch (MyException ex) {
            // Assert
            verify(kafkaService, times(1)).sendMessage(anyString(), anyString());
            verify(weatherService, times(1)).getWeather(eq(cityName));
            assertEquals("Listeleme Başarısız", ex.getMessage());
        }
    }

    @Test
    public void testGetCurrentWeather_Success() {
        // Arrange
        String cityName = "London";
        CurrentWeatherDTO currentWeatherDTO = new CurrentWeatherDTO();

        when(weatherService.getCurrentWeather(eq(cityName))).thenReturn(currentWeatherDTO);

        // Act
        ResponseEntity<DataResult<CurrentWeatherDTO>> response = weatherController.getCurrentWeather(cityName);

        // Assert
        verify(kafkaService, times(1)).sendMessage(anyString(), anyString());
        verify(weatherService, times(1)).getCurrentWeather(eq(cityName));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Listelendi", response.getBody().getMessage());
        assertEquals(currentWeatherDTO, response.getBody().getData());
    }

    @Test
    public void testGetCurrentWeather_Failure() {
        // Arrange
        String cityName = "London";

        when(weatherService.getCurrentWeather(eq(cityName))).thenReturn(null);

        // Act
        try {
            weatherController.getCurrentWeather(cityName);
        } catch (MyException ex) {
            // Assert
            verify(kafkaService, times(1)).sendMessage(anyString(), anyString());
            verify(weatherService, times(1)).getCurrentWeather(eq(cityName));
            assertEquals("Listeleme Başarısız", ex.getMessage());
        }
    }
}