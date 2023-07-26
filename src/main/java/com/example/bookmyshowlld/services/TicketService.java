package com.example.bookmyshowlld.services;

import com.example.bookmyshowlld.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshowlld.models.ShowSeat;
import com.example.bookmyshowlld.models.ShowSeatState;
import com.example.bookmyshowlld.models.Ticket;
import com.example.bookmyshowlld.models.TicketStatus;
import com.example.bookmyshowlld.repositories.ShowRepository;
import com.example.bookmyshowlld.repositories.ShowSeatRepository;
import com.example.bookmyshowlld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private ShowRepository showRepository;
    @Autowired
    public TicketService(ShowSeatRepository showSeatRepository,
                         UserRepository userRepository,
                         ShowRepository showRepository) {
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(Long showId, List<Long> showSeatIds, Long userId) throws ShowSeatNotAvailableException {
        List<ShowSeat> showSeats = this.showSeatRepository.findByIdIn(showSeatIds);

        for(ShowSeat showSeat: showSeats) {
            if(showSeat.getState() != ShowSeatState.AVAILABLE) {
                throw new ShowSeatNotAvailableException("Seat with " +
                        showSeat.getId() + " not available");
            }
        }

        for(ShowSeat showSeat: showSeats) {
            showSeat.setState(ShowSeatState.LOCKED);
            showSeatRepository.save(showSeat);
        }

        Ticket ticket = new Ticket();
        ticket.setTicketStatus(TicketStatus.PENDING);
        ticket.setBookedBy(userRepository.findById(userId).get());
        ticket.setShow(showRepository.findShowById(showId).get());
        ticket.setShowSeats(showSeats);
        ticket.setTimeOfBooking(new Date());

        return ticket;
    }
}
