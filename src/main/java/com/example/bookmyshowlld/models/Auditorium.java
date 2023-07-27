package com.example.bookmyshowlld.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Auditorium extends BaseModel {
    private String name;

    //Auditorium : Seat
    //  1 : M (one auditorium can have many seats)
    //  1 : 1 (one seat only belongs to one auditorium)
    @OneToMany(fetch = FetchType.EAGER)
    private List<Seat> seats = new ArrayList<>();
    private int capacity;

    //Auditorium:Auditorium Feature
    // 1 : M (one audit can have many features)
    // M : 1 (one feature can be a part of many auditorium)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AuditoriumFeature> auditoriumFeatures;

    //Auditorium:Theatre
    //   1 : 1 (one audit can only belong to one theatre)
    //   M : 1 (one theatre can have many audito)
    @ManyToOne
    private Theatre theatre;
}
