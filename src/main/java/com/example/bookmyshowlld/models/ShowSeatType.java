package com.example.bookmyshowlld.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "show_seattype_mapping")
public class ShowSeatType extends BaseModel {
    // SST : Show
    // 1 : 1 (one sst will have only one show)
    // M : 1 (one Show can have many sst)
    @ManyToOne
    private Show show;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private double price;
}
