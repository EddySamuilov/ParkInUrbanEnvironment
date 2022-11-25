package com.example.carpark.repository;

import com.example.carpark.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findByIdAndNeighbourhoodAndStreet(String id, String neighbourhood, String numberOfStreet);

    Address findByNeighbourhood(String neighbourhood);
}
