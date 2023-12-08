package com.supriya.Booking_Service.services;

import com.supriya.Booking_Service.models.SeatPricing;
import com.supriya.Booking_Service.repositories.SeatPricingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatPricingService {

    @Autowired
    private SeatPricingRepo seatPricingRepo;
    public SeatPricing getSeatPriceBySeatClass(String seatClass) {
        return seatPricingRepo.findBySeatClass(seatClass);
    }
}
