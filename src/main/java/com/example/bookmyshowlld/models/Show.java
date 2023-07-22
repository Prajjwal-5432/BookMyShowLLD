package com.example.bookmyshowlld.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Show extends BaseModel {
    //Show : Movie
    // 1 : 1 (one show can have only one movie)
    // M : 1 (one movie can have many shows)
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;

    //Show : Auditorium
    // 1 : 1 (one show can belong to only one auditorium
    // M : 1 (one audito can have many shows)
    @ManyToOne
    private Auditorium auditorium;

    //Show : ShowSeat
    // 1 : M (one show can have many show seats)
    // 1 : 1 (one show seat only belongs to one show)
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;

    //Show : ShowSeatType
    // 1 : M (one show can have many showseattype)
    // 1 : 1 (one showseat belongs to only one show)
    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ShowFeature> showFeatures;
}
