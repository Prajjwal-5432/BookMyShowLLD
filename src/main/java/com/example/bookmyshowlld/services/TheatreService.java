package com.example.bookmyshowlld.services;

import com.example.bookmyshowlld.exceptions.CityNotFoundException;
import com.example.bookmyshowlld.models.City;
import com.example.bookmyshowlld.models.Theatre;
import com.example.bookmyshowlld.repositories.CityRepository;
import com.example.bookmyshowlld.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TheatreService {
    private TheatreRepository theatreRepository;
    private CityRepository cityRepository;
    @Autowired
    public TheatreService(TheatreRepository theatreRepository, CityRepository cityRepository) {
        this.theatreRepository = theatreRepository;
        this.cityRepository = cityRepository;
    }

    public Theatre createTheatre(String name, String address, Long cityId) throws CityNotFoundException {
        //Create a theatre object
        //Fetch the city for the id
        //Add the theatre for to the city
        //Save the city and the theatre

        Optional<City> cityOptional = cityRepository.findById(cityId);
        if(!cityOptional.isPresent()) {
            throw new CityNotFoundException("No City with given id");
        }

        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setAddress(address);

        Theatre savedTheatre = theatreRepository.save(theatre);

        City dbCity = cityOptional.get();
        if(dbCity.getTheatres() == null) {
            dbCity.setTheatres(new ArrayList<>());
        }
        dbCity.getTheatres().add(savedTheatre);
        cityRepository.save(dbCity);

        return savedTheatre;
    }
}
