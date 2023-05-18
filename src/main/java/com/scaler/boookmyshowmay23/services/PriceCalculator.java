package com.scaler.boookmyshowmay23.services;

import com.scaler.boookmyshowmay23.models.Show;
import com.scaler.boookmyshowmay23.models.ShowSeat;
import com.scaler.boookmyshowmay23.models.ShowSeatType;
import com.scaler.boookmyshowmay23.repositories.ShowSeatRepository;
import com.scaler.boookmyshowmay23.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> seats, Show show) {
        // 1. Get ShowSeatType for that show
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;

        // 2. Get seattype for all seats
        for (ShowSeat showSeat: seats) {
            for (ShowSeatType showSeatType: showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        // 3. Add amount of all
        return amount;
    }
}
