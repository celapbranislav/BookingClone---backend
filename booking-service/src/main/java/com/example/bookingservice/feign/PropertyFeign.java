package com.example.bookingservice.feign;

import com.example.bookingservice.dto.PropertyDTO;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("property-service")
public interface PropertyFeign {

    @GetMapping("api/properties/{idProperty}")
    public PropertyDTO getPropertyById(@PathVariable @Min(1) int idProperty);


}
