package com.example.propertyservice.dto;

import com.example.propertyservice.models.Amenity;

import java.math.BigDecimal;
import java.util.Set;

public record PropertyCreateDTO(
        String name,
        String description,
        String address,
        String city,
        String country,
        BigDecimal pricePerNight,
        Integer maxGuests,
        Set<Integer> amenities
) {
}
