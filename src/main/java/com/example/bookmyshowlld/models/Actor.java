package com.example.bookmyshowlld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Actor extends BaseModel {
    private String name;
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}

//one actor can be in many movies and one move can have many actors so many to many;