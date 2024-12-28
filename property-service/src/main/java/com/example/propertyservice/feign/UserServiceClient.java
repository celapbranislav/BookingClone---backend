package com.example.propertyservice.feign;

import com.example.propertyservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/auth/users/{id}")
    User getUserById(@PathVariable("id") Integer id);

}
