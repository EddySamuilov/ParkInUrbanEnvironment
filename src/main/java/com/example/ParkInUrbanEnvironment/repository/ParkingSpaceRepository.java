package com.example.carpark.repository;

import com.example.carpark.entity.Address;
import com.example.carpark.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, String> {
    ParkingSpace findByAddress(Address address);
}
