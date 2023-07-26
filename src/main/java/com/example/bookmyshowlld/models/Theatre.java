package com.example.bookmyshowlld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Theatre extends BaseModel {
    private String name;
    private String address;

    @OneToMany(mappedBy = "theatre", fetch = FetchType.EAGER)
    private List<Auditorium> auditoriums = new ArrayList<>();

    //Theatre : Show
    // 1 : M (one theatre can have many shows)
    // 1 : 1 (one show can be played in only one theatre)
    @OneToMany
    private List<Show> upcomingShows;
}
