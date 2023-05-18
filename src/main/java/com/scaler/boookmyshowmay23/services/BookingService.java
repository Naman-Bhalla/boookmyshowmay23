package com.scaler.boookmyshowmay23.services;

import com.scaler.boookmyshowmay23.models.*;
import com.scaler.boookmyshowmay23.repositories.BookingRepository;
import com.scaler.boookmyshowmay23.repositories.ShowRepository;
import com.scaler.boookmyshowmay23.repositories.ShowSeatRepository;
import com.scaler.boookmyshowmay23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private PriceCalculator priceCalculator;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository, UserRepository userRepository,
                          PriceCalculator priceCalculator) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculator = priceCalculator;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId) {
       // start transaction;
        // --- TODAY WE WILL START LOCK HERE
        // 1. Get user with that userId
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw  new RuntimeException();
        }

        User bookedBy = userOptional.get();

        // 2. Get show with that showId
        Optional<Show> showOptional = showRepository.findById(showId);

        if (showOptional.isEmpty()) {
            throw new RuntimeException();
        }

        Show bookedShow = showOptional.get();

        // -------------- TAKE LOCK ---------------
        // 3. Get ShowSeat with that seatIds

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);

        // 4. Check if all show seats are available
        // 5. If no, throw error
        for (ShowSeat showSeat: showSeats) {
            if (!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15
            ))) {
                throw new RuntimeException();
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        // 6. If yes, Mark the status of show seats as LOCKED
        for (ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            // 7. Save updated show seats to DB
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        // ---------------END LOCK -----------------
        // 8. Create corresponding booking object
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);
        booking.setAmount(priceCalculator.calculatePrice(savedShowSeats, bookedShow));
        booking.setPayments(new ArrayList<>());

        // 9. Return boking object
        // TODAY WE WILL END LOCK THERE
        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }
}
