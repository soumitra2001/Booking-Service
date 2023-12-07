package com.supriya.Booking_Service.repositories;

import com.supriya.Booking_Service.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUserEmail(String userEmail);
}
