package com.example.propertyservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Invalid Name: Empty name")
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @NotBlank(message = "Invalid Description: Empty description")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Invalid Address: Empty address")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Positive(message = "Invalid price: Price needs to be positive")
    @Column(name = "price_per_night", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    @Positive
    @Column(name = "max_guests", nullable = false)
    private Integer maxGuests;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "host_id", nullable = false)
    private User host;


}