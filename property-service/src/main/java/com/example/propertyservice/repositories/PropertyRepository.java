package com.example.propertyservice.repositories;

import com.example.propertyservice.models.Amenity;
import com.example.propertyservice.models.Property;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query("SELECT p FROM Property p JOIN p.amenities a " +
            "WHERE p.country = :country AND p.pricePerNight <= :maxPrice AND a.id IN :amenityIds")
    List<Property> findByCountryAndPriceAndAmenities(String country, BigDecimal maxPrice, List<Integer> amenityIds, Sort sort);

}
