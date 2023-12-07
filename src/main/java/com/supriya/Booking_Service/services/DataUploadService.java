package com.supriya.Booking_Service.services;

import com.supriya.Booking_Service.models.Seat;
import com.supriya.Booking_Service.models.SeatPricing;
import com.supriya.Booking_Service.repositories.SeatPricingRepo;
import com.supriya.Booking_Service.repositories.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;

@Service
public class DataUploadService {

    @Autowired
    private SeatPricingRepo seatPricingRepo;

    @Autowired
    private SeatRepo seatRepo;

    public void uploadSeatPricingDefaultData(String filePath){
        try {
            // Load CSV file from the resources folder
            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while ((line=reader.readLine()) != null){
                String[] data = line.split(",");

                SeatPricing seatPrice=new SeatPricing();
                seatPrice.setSeatClass(data[1]);
                // Check every price it is null or not and fetch the price
                String minPrice=data[2].length()>1 ? data[2].substring(1) : null;
                String normalPrice=data[3].length()>1 ? data[3].substring(1) : null;
                String maxPrice= data.length>4 ? data[4].substring(1) : null;

                // null check for every price to convert it into BigDecimal
                if (minPrice != null) {
                    seatPrice.setMinPrice(new BigDecimal(minPrice));
                }
                if(normalPrice != null){
                    seatPrice.setNormalPrice(new BigDecimal(normalPrice));
                }
                if(maxPrice != null){
                    seatPrice.setMaxPrice(new BigDecimal(maxPrice));
                }

                seatPricingRepo.save(seatPrice);

            }

            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void uploadSeatsDefaultData(String filePath){
        System.out.println(filePath);
        try {
            // Load CSV file from the resources folder

            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while ((line=reader.readLine()) != null){
                String[] data = line.split(",");

                Seat seat=new Seat();
                seat.setSeatClass(data[2]);
                seat.setBooked(false);
                seatRepo.save(seat);

            }

            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


