package com.scaler.boookmyshowmay23.controllers;

import com.scaler.boookmyshowmay23.dtos.BookMovieRequestDto;
import com.scaler.boookmyshowmay23.dtos.BookMovieResponseDto;
import com.scaler.boookmyshowmay23.dtos.ResponseStatus;
import com.scaler.boookmyshowmay23.models.Booking;
import com.scaler.boookmyshowmay23.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

// @Controller // @Service // @Repository -> just a tag to tell spring that this is a special class
@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired // -> Automatically find an object of the params, create it and send here
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto request) {
        BookMovieResponseDto response = new BookMovieResponseDto();

        Booking booking;

        try {
            booking = bookingService.bookMovie(
                    request.getUserId(),
                    request.getShowSeatIds(),
                    request.getShowId()
            );

            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setBookingId(booking.getId());
            response.setAmount(booking.getAmount());
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}
