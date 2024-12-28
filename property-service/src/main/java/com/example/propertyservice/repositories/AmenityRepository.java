package com.example.propertyservice.repositories;

import com.example.propertyservice.models.Amenity;
import feign.template.QueryTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {
    @Query("SELECT a FROM Amenity a WHERE a.id IN :amenityIds")
    Set<Amenity> findByIds(Set<Integer> amenityIds);
}
