package com.example.carpark.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {
    private String brand;
    private String registrationNumber;
    private String model;
}
