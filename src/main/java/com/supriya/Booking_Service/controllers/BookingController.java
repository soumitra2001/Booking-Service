package com.supriya.Booking_Service.controllers;

import com.supriya.Booking_Service.dto.BookingRequest;
import com.supriya.Booking_Service.models.Booking;
import com.supriya.Booking_Service.services.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/booking")
    public ResponseEntity<String> bookYourSeats(@Valid @RequestBody BookingRequest request){
        String response=bookingService.bookSeats(request);
        if(response.contains("!"))return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/bookings")
    public ResponseEntity<String> getMyBookings(@RequestParam String userEmail){
        return bookingService.getAllBookingByUserEmail(userEmail);
    }

}
