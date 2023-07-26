package com.example.bookmyshowlld.repositories;

import com.example.bookmyshowlld.models.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
    Auditorium save(Auditorium auditorium);

    Optional<Auditorium> findById(Long id);
}
