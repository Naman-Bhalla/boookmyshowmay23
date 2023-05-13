package com.scaler.boookmyshowmay23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @ManyToMany
    private List<ShowSeat> showSeats; // B: SS -> 1: m =>  M:1

    @ManyToOne
    private User user;
    // 1 booking by 1 user
    // 1 user can make many bookings

    private Date bookedAt;

    @ManyToOne
    private Show show;  // B : S => 1 booking is for 1 show. 1 show can have many booking

    private int amount;


    // 1 : M
    // 1  : 1
    @OneToMany
    private List<Payment> payments;
}
