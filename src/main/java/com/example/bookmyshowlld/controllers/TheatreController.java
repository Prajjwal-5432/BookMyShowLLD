package com.example.bookmyshowlld.controllers;

import com.example.bookmyshowlld.exceptions.CityNotFoundException;
import com.example.bookmyshowlld.models.SeatType;
import com.example.bookmyshowlld.models.ShowSeatType;
import com.example.bookmyshowlld.models.Theatre;
import com.example.bookmyshowlld.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class TheatreController {
    private TheatreService theatreService;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public Theatre createTheatre(String name, String address, Long cityId) throws CityNotFoundException {
        Theatre theatre = null;
        try {
            theatre = this.theatreService.createTheatre(name, address, cityId);
        } catch (CityNotFoundException e) {
            System.out.println("Something went wrong");
        }
        return theatre;
    }

    public Theatre addAuditorium(Long theatreId, String name, int capacity) {
        return theatreService.addAuditorium(theatreId, name, capacity);
    }

    public void addSeats(Long auditoriumId, Map<SeatType, Integer> seatCount) {
        theatreService.addSeats(auditoriumId, seatCount);
    }
}
