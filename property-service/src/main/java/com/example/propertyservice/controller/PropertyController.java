package com.example.propertyservice.controller;


import com.example.propertyservice.exceptions.EntityNotFoundException;
import com.example.propertyservice.models.Property;
import com.example.propertyservice.models.User;
import com.example.propertyservice.service.PropertyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{idProperty}")
    public Property getPropertyById(@PathVariable @Min(1) int idProperty) {
        return propertyService.getPropertyById(idProperty);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProperty(@RequestBody @Valid Property property) {
        User user = propertyService.findUserById(property.getHost().getId());
        return new ResponseEntity<>(propertyService.saveProperty(property), HttpStatus.CREATED);

    }

    @PutMapping("/updateProperty")
    public ResponseEntity<?> updateProperty(@RequestBody @Valid Property property) {
            return new ResponseEntity<>(propertyService.updateProperty(property), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Property> getPropertiesBy(@RequestParam String country, @RequestParam BigDecimal maxPrice) {
        return propertyService.getPropertiesByCountryAndPriceAndAmenities(country, maxPrice);
    }

}
