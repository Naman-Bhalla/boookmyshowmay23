package com.scaler.boookmyshowmay23.repositories;

import com.scaler.boookmyshowmay23.controllers.BookingController;
import com.scaler.boookmyshowmay23.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

// <Id, Object>