package com.scaler.boookmyshowmay23.repositories;

import com.scaler.boookmyshowmay23.controllers.BookingController;
import com.scaler.boookmyshowmay23.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Override
    Optional<Booking> findById(Long aLong);

    @Override
    Booking save(Booking entity);
}

// <Id, Object>