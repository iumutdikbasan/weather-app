package com.iumutdikbasan.weatherapp.service.abstracts;


import com.iumutdikbasan.weatherapp.entity.City;

import java.util.List;

public interface CityService {

    City save(City city);
    List<City> findByUserId(Long id);

    List<City> getAllByOrderByCityNameAsc();

    List<City> getByCityNameContaining(String cityName);

    List<City> getByCityNameStartsWith(String cityName);

    List<City> getCitiesPage(int pageNumber, int pageSize);


}