package com.supriya.Booking_Service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String seatClass;

    private BigDecimal minPrice;

    private BigDecimal normalPrice;

    private BigDecimal maxPrice;

}
