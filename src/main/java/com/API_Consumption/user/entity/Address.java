package com.API_Consumption.user.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "addresses")
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @Embedded
    private Geo geo;

}