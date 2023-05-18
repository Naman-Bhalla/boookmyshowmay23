package com.scaler.boookmyshowmay23.repositories;

import com.scaler.boookmyshowmay23.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Override
    Optional<Show> findById(Long aLong);
}

// 1. Class -> Interface
// 2. Extends JpaRepository