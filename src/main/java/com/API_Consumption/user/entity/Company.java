package com.API_Consumption.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "companies")
public class Company {

    @Column(name = "company_name")
    private String name;
    private String catchPhrase;
    private String bs;

}
