package com.example.propertyservice.controller;


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

import java.util.List;

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
        if (user != null) {
            return new ResponseEntity<>(propertyService.saveProperty(property), HttpStatus.CREATED);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nije pronadjen Host sa tim id-jem");
        }
    }
   
}
