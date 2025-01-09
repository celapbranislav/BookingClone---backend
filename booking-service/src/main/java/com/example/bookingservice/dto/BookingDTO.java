package com.example.bookingservice.dto;

import com.example.bookingservice.models.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;

public record BookingDTO(
        @NotNull(message = "User id cannot be null") Integer userId,
        @NotNull(message = "Property id cannot be null") Integer propertyId,
        Date checkIn,
        Date checkOut,
        BigDecimal totalPrice,
        Status status
) {
}
