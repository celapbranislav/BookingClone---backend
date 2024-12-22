package com.example.propertyservice.controller;


import com.example.propertyservice.models.Property;
import com.example.propertyservice.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Property getPropertyById(@PathVariable int idProperty) {
        return propertyService.getPropertyById(idProperty);
    }

   public String hello(){
        return "Hello World";
   }

}
