package com.example.propertyservice.service;


import com.example.propertyservice.exceptions.EntityNotFoundException;
import com.example.propertyservice.models.Property;
import com.example.propertyservice.models.User;
import com.example.propertyservice.repositories.PropertyRepository;
import com.example.propertyservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public User findUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Integer id) {
        return propertyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Property with id " + id + " not found!"));
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }


}
