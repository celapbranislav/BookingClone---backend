package com.example.propertyservice.service;


import com.example.propertyservice.exceptions.EntityNotFoundException;
import com.example.propertyservice.exceptions.UserIsNotHostException;
import com.example.propertyservice.models.Property;
import com.example.propertyservice.models.User;
import com.example.propertyservice.repositories.AmenityRepository;
import com.example.propertyservice.repositories.PropertyRepository;
import com.example.propertyservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final AmenityRepository amenityRepository;

    public User findUserById(Integer id) {
       return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Integer id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property with id " + id + " not found!"));
    }

    public Property saveProperty(Property property) {
        User user = userRepository.findById(property.getHost().getId()).get();
        if(user.getRole().equals("host")) {
            return propertyRepository.save(property);
        } else{
            throw new UserIsNotHostException("User with id " + property.getHost().getId() + " is not host!");
        }

    }

    public Property updateProperty(Property property) {
        Property p = propertyRepository.findById(property.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Property not found"));

        BeanUtils.copyProperties(property, p, "id");

        return propertyRepository.save(p);

    }

    public List<Property> getPropertiesByCountryAndPriceAndAmenities(
            String country,
            BigDecimal maxPrice,
            List<Integer> amenityIds,
            String sortField,
            String sortDirection){
        Sort sorted = Sort.unsorted();
        if (sortField != null && sortDirection != null) {
            sorted = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        }

        return propertyRepository.findByCountryAndPriceAndAmenities(country, maxPrice, amenityIds, sorted);
    }


}
