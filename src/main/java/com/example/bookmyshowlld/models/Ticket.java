package com.example.bookmyshowlld.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Ticket extends BaseModel {
    //Ticket : User
    // 1 : 1 (one ticket can have many user)
    // M : 1 (one user can have many tickets)
    @ManyToOne
    private User bookedBy;

    //Ticket : Show
    // 1 : 1 (one ticket can have one shows)
    // M : 1 (one show can have many tickets)
    @ManyToOne
    private Show show;

    //Ticket : ShowSeat
    // 1 : M (one ticket can buy many showseats)
    // M : 1 (one showseat can be booked by many tickets if cancellations are allowed
    //        if canellation was not allowed than 1 : 1)
    @ManyToMany
    private List<ShowSeat> showSeats;
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    private Date timeOfBooking;
}
