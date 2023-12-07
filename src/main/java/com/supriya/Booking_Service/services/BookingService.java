package com.supriya.Booking_Service.services;

import com.supriya.Booking_Service.dto.BookingRequest;
import com.supriya.Booking_Service.models.Booking;
import com.supriya.Booking_Service.models.Seat;
import com.supriya.Booking_Service.notifications.MailHandler;
import com.supriya.Booking_Service.repositories.BookingRepo;
import com.supriya.Booking_Service.repositories.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private SeatService seatService;

    // Create a booking for the selected seats
    public String bookSeats(BookingRequest request){

        // get the all seats requested by the user
        List<Seat> seats=seatService.findAllById(request.getSeatIds());
        // If any of the id invalid we should return an error response
        if(seats.isEmpty()){
            return "Please enter valid Seat's Ids for booking your Seats!";
        }

        // Now checking each seat if it is booked or not, if any seat or seats are booked show the user the booked
        // seats ids and request for try with another ids =>
        List<Long> ids=new ArrayList<>();
        long amount=0L;
        for(Seat seat:seats){
            if(seat.isBooked())ids.add(seat.getId());
            else {
                seat.setBooked(true);
                long price=Long.parseLong(seatService.getSeatPrice(seat.getId()).get(1).split(" ")[5]);
                amount += price;
            }
        }
        if(ids.isEmpty()){
            Booking booking=new Booking();
            booking.setSeats(seats);
            booking.setUserName(request.getUserName());
            booking.setUserEmail(request.getUserEmail());
            booking.setPhoneNumber(request.getPhoneNumber());

            //save to the booking information
            Booking bookedSeats = bookingRepo.save(booking);

            // Send notification to user's email
            MailHandler.getInstance().sendMail(bookedSeats.getId(),bookedSeats.getUserName(),bookedSeats.getUserEmail(),amount);

            // Formatting the output 😋
            return ("Booking Details:- \n"+"Booking ID: "+bookedSeats.getId()+"\n"+"No of Seats: "+seats.size()
            +"\n"+"Total amount: "+amount+"\n");

        }

        // Formatting the output 😋
        return ("Seats can not be booked because Seats with IDs "+ids.toString()+" are already booked.\nPlease try with another IDs");
    }

    // Return all bookings created by the user
    public ResponseEntity<String> getAllBookingByUserEmail(String userEmail) {
        List<Booking> booking=bookingRepo.findAllByUserEmail(userEmail);

        // Check if valid email is passed or any user is present with this email or not
        if(booking.isEmpty())return new ResponseEntity<>("Invalid userEmail\nEnter a valid userEmail!", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(booking.toString(),HttpStatus.OK);
    }

}
