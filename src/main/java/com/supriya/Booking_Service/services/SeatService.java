package com.supriya.Booking_Service.services;

import com.supriya.Booking_Service.models.Seat;
import com.supriya.Booking_Service.models.SeatPricing;
import com.supriya.Booking_Service.repositories.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private SeatPricingService seatPricingService;

    // Return all the seats
    public List<Seat> getAllSeat() {
        return seatRepo.findAll();
    }

    // Return the seat details along with the pricing for the seat based on the SeatClass
    public List<String> getSeatPrice(long id){
        List<String> response=new ArrayList<>();

        // fetch all seats to check the seat is present with the given id or not
        List<Seat> seats=getAllSeat();
        for(Seat seat:seats){
//            System.out.println("my seat");
            //If any seat is present with this given id then only we are good to go with the pricing logic
            if(seat.getId()==id){
                // adding requested seat details into the response
                response.add(seat.toString()+"\n");
                // Checking the booking percentage of this SeatClass
                int bookingPercent=(bookedSeat(seat.getSeatClass())/seatCount())*100;

                // Get the pricing for the SeatClass
                SeatPricing seatPrice=seatPricingService.getSeatPriceBySeatClass(seat.getSeatClass());
                BigDecimal minPrice= seatPrice.getMinPrice();
                BigDecimal normalPrice=seatPrice.getNormalPrice();
                BigDecimal maxPrice=seatPrice.getMaxPrice();

                // Assign the price according to the percentage of booking of seat
                if(bookingPercent<40){
                    minPrice = minPrice==null ? normalPrice : minPrice;
                    response.add("Price for this Seat is: "+minPrice);
                }else if(bookingPercent<=60){
                    normalPrice = normalPrice==null ? maxPrice : normalPrice;
                    response.add("Price for this Seat is: "+normalPrice);
                }else {
                    maxPrice = maxPrice==null ? normalPrice : maxPrice;
                    response.add("Price for this Seat is: "+maxPrice);
                }

                return response;
            }
        }

        response.add("Invalid Seat Id, please check the ID once!");
        return response;
    }


    //Finding the total no of seat (booked and not-booked seats)
    private int seatCount(){
        return (int)seatRepo.count();
    }

    // No of seats which are already booked
    private int bookedSeat(String seatClass){
        return seatRepo.countBySeatClass(seatClass);
    }

    public List<Seat> findAllById(List<Long> seatIds) {
        return seatRepo.findAllById(seatIds);
    }
}
