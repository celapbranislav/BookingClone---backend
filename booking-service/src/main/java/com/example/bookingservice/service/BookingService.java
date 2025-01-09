package com.example.bookingservice.service;

import com.example.bookingservice.dto.BookingDTO;
import com.example.bookingservice.dto.PropertyDTO;
import com.example.bookingservice.exceptions.BookingIsAlreadyConfirmedException;
import com.example.bookingservice.exceptions.DateIsInvalidException;
import com.example.bookingservice.exceptions.EntityNotFoundException;
import com.example.bookingservice.feign.PropertyFeign;
import com.example.bookingservice.models.Booking;
import com.example.bookingservice.models.Status;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PropertyFeign propertyFeign;

    public BookingDTO makeBooking(BookingDTO bookingDTO) {
        if(userRepository.findById(bookingDTO.userId()).isEmpty()) { throw new EntityNotFoundException("User not found");}

        PropertyDTO p = propertyFeign.getPropertyById(bookingDTO.propertyId());
        if(p == null) {throw new EntityNotFoundException("Property not found");}

        long days = ChronoUnit.DAYS.between(bookingDTO.checkIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                bookingDTO.checkOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        if (days <= 0) {
            throw new DateIsInvalidException("Check-out date must be after check-in date");
        }


        BigDecimal totalPrice = p.pricePerNight().multiply(BigDecimal.valueOf(days));


        Booking booking = new Booking();
        booking.setUserId(bookingDTO.userId());
        booking.setPropertyId(bookingDTO.propertyId());
        booking.setCheckIn(bookingDTO.checkIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        booking.setCheckOut(bookingDTO.checkOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(Status.pending);


        bookingRepository.save(booking);
        return mapToBookingDTO(booking);


    }

    private BookingDTO mapToBookingDTO(Booking booking) {
        return new BookingDTO(
                booking.getUserId(),
                booking.getPropertyId(),
                Date.from(booking.getCheckIn().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(booking.getCheckOut().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                booking.getTotalPrice(),
                booking.getStatus()
        );
    }

    public List<Booking> getBookings(Integer idHost){
        return bookingRepository.findByHostId(idHost);
    }

    public BookingDTO confirmBooking(Integer idBooking) {
        Booking booking = bookingRepository.findById(idBooking)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        if (!booking.getStatus().equals(Status.pending)) {
            throw new BookingIsAlreadyConfirmedException("Booking is not in pending status");
        }

        booking.setStatus(Status.confirmed);
        bookingRepository.save(booking);

        return mapToBookingDTO(booking);
    }

    public BookingDTO cancelBooking(Integer idBooking){
        Booking booking = bookingRepository.findById(idBooking)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        if (booking.getStatus().equals(Status.cancelled)) {
            throw new BookingIsAlreadyConfirmedException("Booking is already cancelled");
        }

        booking.setStatus(Status.cancelled);
        bookingRepository.save(booking);

        return mapToBookingDTO(booking);
    }


}
