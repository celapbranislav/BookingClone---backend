package com.example.propertyservice.repositories;

import com.example.propertyservice.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query("SELECT p FROM Property p WHERE p.country = :country AND p.pricePerNight <= :maxPrice")
    List<Property> findByCountryAndMinPricePerNight(String country, BigDecimal maxPrice);

}
