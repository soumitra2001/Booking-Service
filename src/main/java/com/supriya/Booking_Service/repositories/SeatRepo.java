package com.supriya.Booking_Service.repositories;

import com.supriya.Booking_Service.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Long> {


    @Query(value = "select count(*) from seat where seat.seat_class= :seatClass and seat.is_booked=true",nativeQuery = true)
    int countBySeatClass(@Param("seatClass") String seatClass);
}
