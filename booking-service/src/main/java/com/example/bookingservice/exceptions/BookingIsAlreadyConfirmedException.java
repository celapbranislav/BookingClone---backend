package com.example.bookingservice.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingIsAlreadyConfirmedException extends RuntimeException {
    private String message;
}
