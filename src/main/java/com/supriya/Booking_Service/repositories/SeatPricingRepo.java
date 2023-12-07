package com.supriya.Booking_Service.repositories;

import com.supriya.Booking_Service.models.SeatPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatPricingRepo extends JpaRepository<SeatPricing, Long> {


    @Query(value = "select * from seat_pricing where seat_pricing.seat_class= :seatClass",nativeQuery = true)
    SeatPricing findBySeatClass(@Param("seatClass") String seatClass);
}
