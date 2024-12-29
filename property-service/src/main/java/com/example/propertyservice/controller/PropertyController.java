package com.example.propertyservice.controller;



import com.example.propertyservice.dto.PropertyCreateDTO;
import com.example.propertyservice.dto.PropertyDTO;
import com.example.propertyservice.dto.PropertyUpdateDTO;
import com.example.propertyservice.models.Property;
import com.example.propertyservice.service.PropertyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public List<PropertyDTO> getAllProperties() {
        return propertyService.getProperties();
    }

    @GetMapping("/{idProperty}")
    public PropertyDTO getPropertyById(@PathVariable @Min(1) int idProperty) {
        return propertyService.getPropertyById(idProperty);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProperty(@RequestBody @Valid PropertyCreateDTO property, @RequestParam Integer idUser) {
        return new ResponseEntity<>(propertyService.saveProperty(property, idUser), HttpStatus.CREATED);

    }

    @PutMapping("/updateProperty")
    public ResponseEntity<?> updateProperty(@RequestBody @Valid PropertyUpdateDTO property) {
            return new ResponseEntity<>(propertyService.updateProperty(property), HttpStatus.OK);
    }

    //Mi ne mozemo poslati List<Aminty> preko query-a jer java ne zna kako da barata sa tim stvarima
    @GetMapping("/search")
    public List<PropertyDTO> getPropertiesBy(
            @RequestParam String country,
            @RequestParam @Positive BigDecimal maxPrice,
            @RequestParam List<Integer> amenityIds,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {
        return propertyService.getPropertiesByCountryAndPriceAndAmenities(country, maxPrice, amenityIds, sortField, sortDirection);
    }

    @DeleteMapping("/{idProperty}")
    public ResponseEntity<?> deleteProperty(@PathVariable @Min(1) Integer idProperty) {
        propertyService.deleteProperty(idProperty);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
