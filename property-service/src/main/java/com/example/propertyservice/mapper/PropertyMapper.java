package com.example.propertyservice.mapper;


import com.example.propertyservice.dto.PropertyDTO;
import com.example.propertyservice.dto.ReviewDTO;
import com.example.propertyservice.models.Property;
import com.example.propertyservice.models.Review;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PropertyMapper {

    public PropertyDTO maptoPropertyDTO(Property property) {
        return new PropertyDTO(
                property.getName(),
                property.getDescription(),
                property.getAddress(),
                property.getCity(),
                property.getCountry(),
                property.getPricePerNight(),
                property.getMaxGuests(),
                property.getAmenities(),
                property.getReviews().stream()
                        .map(this::mapToReviewDTO)
                        .collect(Collectors.toList())
        );
    }

    public ReviewDTO mapToReviewDTO(Review review) {
        return new ReviewDTO(
                review.getUser().getName(),
                review.getRating(),
                review.getComment()
        );
    }

}
