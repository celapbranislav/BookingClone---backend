package com.example.propertyservice.service;


import com.example.propertyservice.dto.PropertyCreateDTO;
import com.example.propertyservice.dto.PropertyDTO;
import com.example.propertyservice.dto.PropertyUpdateDTO;
import com.example.propertyservice.dto.ReviewDTO;
import com.example.propertyservice.exceptions.EntityNotFoundException;
import com.example.propertyservice.exceptions.UserIsNotHostException;
import com.example.propertyservice.mapper.PropertyMapper;
import com.example.propertyservice.models.Amenity;
import com.example.propertyservice.models.Property;
import com.example.propertyservice.models.PropertyAmenity;
import com.example.propertyservice.models.User;
import com.example.propertyservice.repositories.AmenityRepository;
import com.example.propertyservice.repositories.PropertyAmenityRepository;
import com.example.propertyservice.repositories.PropertyRepository;
import com.example.propertyservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final PropertyAmenityRepository propertyAmenities;
    private final PropertyMapper propertyMapper;
    private final AmenityRepository amenityRepository;

    public List<PropertyDTO> getProperties(){
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(propertyMapper::maptoPropertyDTO).collect(Collectors.toList());
    }

    public PropertyDTO getPropertyById(Integer id) {
        Property p = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property with id " + id + " not found!"));

        return propertyMapper.maptoPropertyDTO(p);

    }

    public Property saveProperty(PropertyCreateDTO property, Integer idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + idUser + " not found!"));

        if(user.getRole().equals("host")) {
            Property p = new Property();
            p.setName(property.name());
            p.setDescription(property.description());
            p.setAddress(property.address());
            p.setCity(property.city());
            p.setCountry(property.country());
            p.setPricePerNight(property.pricePerNight());
            p.setMaxGuests(property.maxGuests());
            Set<Amenity> amenities = amenityRepository.findByIds(property.amenities());
            p.setAmenities(amenities);
            p.setHost(user);

            return propertyRepository.save(p);
        } else{
            throw new UserIsNotHostException("User with id " + idUser + " is not host!");
        }

    }

    public Property updateProperty(PropertyUpdateDTO property) {
        Property p = propertyRepository.findById(property.propertyId())
                        .orElseThrow(() -> new EntityNotFoundException("Property not found"));

        p.setName(property.name());
        p.setDescription(property.description());
        p.setAddress(property.address());
        p.setCity(property.city());
        p.setCountry(property.country());
        p.setPricePerNight(property.pricePerNight());
        p.setMaxGuests(property.maxGuests());

        Set<Amenity> amenities = amenityRepository.findByIds(property.amenities());
        p.setAmenities(amenities);

        return propertyRepository.save(p);

    }

    public List<PropertyDTO> getPropertiesByCountryAndPriceAndAmenities(
            String country,
            BigDecimal maxPrice,
            List<Integer> amenityIds,
            String sortField,
            String sortDirection){
        Sort sorted = Sort.unsorted();
        if (sortField != null && sortDirection != null) {
            sorted = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        }

        List<Property> properties = propertyRepository.findByCountryAndPriceAndAmenities(country, maxPrice, amenityIds, sorted);
        return properties.stream().map(propertyMapper::maptoPropertyDTO).collect(Collectors.toList());
    }

    public void deleteProperty(Integer id) {
        Property p = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));

        List<PropertyAmenity> pa = propertyAmenities.findAllByProperty(p);
        if(!pa.isEmpty()){ propertyAmenities.deleteAll(pa); }

        propertyRepository.delete(p);
    }

}
