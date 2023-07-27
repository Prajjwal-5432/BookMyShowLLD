package com.example.bookmyshowlld.services;

import com.example.bookmyshowlld.models.*;
import com.example.bookmyshowlld.repositories.AuditoriumRepository;
import com.example.bookmyshowlld.repositories.MovieRepository;
import com.example.bookmyshowlld.repositories.ShowRepository;
import com.example.bookmyshowlld.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ShowService {
    private ShowRepository showRepository;
    private AuditoriumRepository auditoriumRepository;
    private ShowSeatRepository showSeatRepository;
    @Autowired
    public ShowService(ShowRepository showRepository,
                       AuditoriumRepository auditoriumRepository,
                       ShowSeatRepository showSeatRepository) {
        this.showRepository = showRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.showSeatRepository = showSeatRepository;
    }
    public Show createShow(Long movieId, Date startTime, Date endTime, Long auditoriumId,
                           Map<SeatType, Integer> seatPricing, Language language) {
        Show show = new Show();
        show.setLanguage(language);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId).get();
        show.setAuditorium(auditorium);

        Show savedShow = showRepository.save(show);
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat: auditorium.getSeats()) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(savedShow);
            showSeat.setSeat(seat);
            showSeat.setState(ShowSeatState.AVAILABLE);
            showSeats.add(showSeatRepository.save(showSeat));
        }

        savedShow.setShowSeats(showSeats);
        return showRepository.save(savedShow);
    }
}
