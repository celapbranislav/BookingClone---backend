package com.example.bookingservice.controller;


import com.example.bookingservice.dto.BookingDTO;
import com.example.bookingservice.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> makeBooking(@RequestBody @Valid BookingDTO bookingDTO) {

        BookingDTO booking = bookingService.makeBooking(bookingDTO);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<?> getBookings(@RequestParam @Min(1) Integer idUser) {
        return new ResponseEntity<>(bookingService.getBookings(idUser), HttpStatus.OK);
    }

    @PutMapping("/{idBooking}/confirm")
    public ResponseEntity<?> confirmBooking(@PathVariable @Min(1) Integer idBooking) {
        return new ResponseEntity<>(bookingService.confirmBooking(idBooking), HttpStatus.OK);
    }

    @PutMapping("/{idBooking}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable @Min(1) Integer idBooking) {
        return new ResponseEntity<>(bookingService.cancelBooking(idBooking), HttpStatus.OK);
    }


}
