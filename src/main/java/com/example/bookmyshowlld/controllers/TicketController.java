package com.example.bookmyshowlld.controllers;

import com.example.bookmyshowlld.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshowlld.models.Ticket;
import com.example.bookmyshowlld.models.User;
import com.example.bookmyshowlld.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketController {
    private TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    public Ticket bookTicket(Long showId, List<Long> showSeatIds, Long userId) throws ShowSeatNotAvailableException {
        return ticketService.bookTicket(showId, showSeatIds, userId);
    }
}
