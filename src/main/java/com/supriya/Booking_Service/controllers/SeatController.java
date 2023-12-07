package com.supriya.Booking_Service.controllers;

import com.supriya.Booking_Service.models.Seat;
import com.supriya.Booking_Service.services.DataUploadService;
import com.supriya.Booking_Service.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private DataUploadService uploadService;


    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeat(){
        List<Seat> seats= seatService.getAllSeat();
        if(seats.isEmpty())return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(seats,HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<String>> getSeatPriceById(@PathVariable Long id){
        List<String> response=seatService.getSeatPrice(id);
        if(response.size()<2)return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/upload-data")
    public ResponseEntity<String> uploadDefaultDataForSeats(@RequestBody String filePath){
        uploadService.uploadSeatsDefaultData(filePath);
        return new ResponseEntity<>("Default data for Seats uploaded successfully!",HttpStatus.OK);
    }

    @PostMapping(value = "/pricing/upload-data")
    public ResponseEntity<String> uploadDefaultDataForSeatPricing(@RequestBody String filePath){
        uploadService.uploadSeatPricingDefaultData(filePath);
        return new ResponseEntity<>("Default data for SeatPricing uploaded successfully!",HttpStatus.OK);
    }
}
