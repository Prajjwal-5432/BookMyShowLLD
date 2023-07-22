package com.example.bookmyshowlld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeat extends BaseModel {
    //ShowSeat : Show
    // 1 : 1
    // M : 1
    @ManyToOne
    private Show show;

    //ShowSeat : Seat
    // 1 : 1 (one showseat belongs to one seat)
    // M : 1 (one show can have many showseats)
    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private ShowSeatState state;
}