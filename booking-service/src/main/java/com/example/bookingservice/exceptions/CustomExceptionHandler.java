package com.example.bookingservice.exceptions;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorEntity> handleEntityNotFound(EntityNotFoundException ex){
        ErrorEntity errorEntity = new ErrorEntity(LocalDate.now(), ex.getMessage(), "Trenutno nema vise detalja za error");
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateIsInvalidException.class)
    public ResponseEntity<ErrorEntity> handleDateIsInvalid(DateIsInvalidException ex){
        ErrorEntity er = new ErrorEntity(LocalDate.now(), ex.getMessage(), "Trenutno nema vise za error");
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingIsNotInPendingStatusException.class)
    public ResponseEntity<ErrorEntity> handleBookingIsAlreadyConfirmed(BookingIsNotInPendingStatusException ex){
        ErrorEntity er = new ErrorEntity(LocalDate.now(), ex.getMessage(), "Trenutno nema vise za error");
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }


}
