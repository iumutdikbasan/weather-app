package com.iumutdikbasan.weatherapp.service;

import com.iumutdikbasan.weatherapp.entity.City;
import com.iumutdikbasan.weatherapp.general.BaseEntityService;
import com.iumutdikbasan.weatherapp.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityEntityService extends BaseEntityService<City, CityRepository> {
    private CityRepository repository;

    public CityEntityService(CityRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<City> findByUserId(Long userId){
        return repository.findCitiesByUserId(userId);
    }

}
