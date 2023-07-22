package com.example.bookmyshowlld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class City extends BaseModel {
    private String name;

    //City:Theatre
    // 1:M (one city can have many theatre)
    // 1:1 (one theatre can belong to only one city)
    @OneToMany
    private List<Theatre> theatres;
}
