package com.example.bookingservice.repository;

import com.example.bookingservice.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM Booking b " +
            "JOIN Property p ON b.propertyId = p.id " +
            "JOIN p.host h " +
            "WHERE h.id = :hostId")
    public List<Booking> findByHostId(Integer hostId);

}
