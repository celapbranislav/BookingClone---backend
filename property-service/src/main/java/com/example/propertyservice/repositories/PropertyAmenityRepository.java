package com.example.propertyservice.repositories;

import com.example.propertyservice.models.Property;
import com.example.propertyservice.models.PropertyAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyAmenityRepository extends JpaRepository<PropertyAmenity, Integer> {
    List<PropertyAmenity> findAllByProperty(Property property);
}
