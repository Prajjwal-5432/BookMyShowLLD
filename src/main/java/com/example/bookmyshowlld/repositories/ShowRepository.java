package com.example.bookmyshowlld.repositories;

import com.example.bookmyshowlld.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findShowById(Long id);
}
