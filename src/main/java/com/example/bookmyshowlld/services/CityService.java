package com.example.bookmyshowlld.services;

import com.example.bookmyshowlld.models.City;
import com.example.bookmyshowlld.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(String name) {
        City city = new City();
        city.setName(name);

        return this.cityRepository.save(city);
    }
}
