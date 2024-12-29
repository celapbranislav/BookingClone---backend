package com.example.propertyservice.dto;

import java.math.BigDecimal;
import java.util.Set;

public record PropertyUpdateDTO(
        Integer propertyId,
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
