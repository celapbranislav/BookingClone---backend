package com.example.bookingservice.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingIsNotInPendingStatusException extends RuntimeException {
    private String message;
}
