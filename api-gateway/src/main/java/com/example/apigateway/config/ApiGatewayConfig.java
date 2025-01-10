package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("property-service", r -> r.path("/api/properties/**")
                        .uri("lb://property-service"))
                .route("booking-service", r -> r.path("/api/bookings/**")
                        .uri("lb://booking-service"))
                .build();
    }

}
