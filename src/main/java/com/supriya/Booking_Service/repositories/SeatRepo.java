package com.supriya.Booking_Service.repositories;

import com.supriya.Booking_Service.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat, Long> {


    int countBySeatClass(String seatClass);
}
