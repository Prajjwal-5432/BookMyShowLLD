package com.example.bookmyshowlld.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel {
    private String name;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Language> languages;

    //Movie:Actor
    // 1 : M (one movie can have many actors)
    // M : 1 (one actor can be in many movies)
    @ManyToMany
    private List<Actor> actors;
    private int length;
    private double rating;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MovieFeature> movieFeatures;
}
