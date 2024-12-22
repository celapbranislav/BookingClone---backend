package com.example.propertyservice.repositories;

import com.example.propertyservice.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
