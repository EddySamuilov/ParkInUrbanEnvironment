package com.example.carpark.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address extends BaseEntity {
    private String city;
    private String neighbourhood;
    private String street;
    private int numberOfStreet;
    private boolean isOccupied;
}
