package com.supriya.Booking_Service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingRequest {

    @Size(min = 1)
    private List<Long> seatIds;

    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String userEmail;

    @Pattern(regexp = "[0-9]{10,14}")
    private String phoneNumber;
}
