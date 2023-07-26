package com.example.bookmyshowlld.services;

import com.example.bookmyshowlld.exceptions.CityNotFoundException;
import com.example.bookmyshowlld.models.*;
import com.example.bookmyshowlld.repositories.AuditoriumRepository;
import com.example.bookmyshowlld.repositories.CityRepository;
import com.example.bookmyshowlld.repositories.SeatRepository;
import com.example.bookmyshowlld.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TheatreService {
    private TheatreRepository theatreRepository;
    private CityRepository cityRepository;
    private AuditoriumRepository auditoriumRepository;
    private SeatRepository seatRepository;
    @Autowired
    public TheatreService(TheatreRepository theatreRepository,
                          CityRepository cityRepository,
                          AuditoriumRepository auditoriumRepository,
                          SeatRepository seatRepository) {
        this.theatreRepository = theatreRepository;
        this.cityRepository = cityRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.seatRepository = seatRepository;
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

    public Theatre addAuditorium(Long theatreId, String name, int capacity) {
        Theatre theatre = theatreRepository.findById(theatreId).get();

        Auditorium auditorium = new Auditorium();
        auditorium.setTheatre(theatre);
        auditorium.setName(name);
        auditorium.setCapacity(capacity);
        auditorium.setTheatre(theatre);

        Auditorium savedAuditorium = auditoriumRepository.save(auditorium);
        theatre.getAuditoriums().add(savedAuditorium);


        return theatreRepository.save(theatre);
    }

    public void addSeats(Long auditoriumId, Map<SeatType, Integer> seatCount) {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId).get();

        List<Seat> seats = new ArrayList<>();
        for(Map.Entry<SeatType, Integer> entry: seatCount.entrySet()) {
            for(int i = 0; i < entry.getValue(); i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(entry.getKey().toString() + Integer.toString(i + 1));
                seat.setSeatType(entry.getKey());
                seats.add(seat);
            }
        }

        List<Seat> savedSeats = seatRepository.saveAll(seats);
        auditorium.setSeats(savedSeats);
        auditoriumRepository.save(auditorium);
    }
}
