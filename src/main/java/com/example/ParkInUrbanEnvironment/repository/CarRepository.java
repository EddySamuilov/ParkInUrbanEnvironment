package com.example.carpark.repository;

import com.example.carpark.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    Car findByRegistrationNumber(String registrationNumber);
}
