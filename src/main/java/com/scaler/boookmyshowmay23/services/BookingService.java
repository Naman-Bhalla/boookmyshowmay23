package com.scaler.boookmyshowmay23.services;

import com.scaler.boookmyshowmay23.models.Booking;
import com.scaler.boookmyshowmay23.repositories.BookingRepository;
import com.scaler.boookmyshowmay23.repositories.ShowRepository;
import com.scaler.boookmyshowmay23.repositories.ShowSeatRepository;
import com.scaler.boookmyshowmay23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId) {
        // --- TODAY WE WILL START LOCK HERE
        // 1. Get user with that userId
        // 2. Get show with that showId
        // -------------- TAKE LOCK ---------------
        // 3. Get ShowSeat with that seatIds
        // 4. Check if all show seats are available
        // 5. If no, throw error
        // 6. If yes, Mark the status of show seats as LOCKED
        // 7. Save updated show seats to DB
        // ---------------END LOCK -----------------
        // 8. Return
        // TODAY WE WILL END LOCK THERE

        return null;
    }
}
