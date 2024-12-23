package com.example.propertyservice.repositories;

import com.example.propertyservice.models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {
}
