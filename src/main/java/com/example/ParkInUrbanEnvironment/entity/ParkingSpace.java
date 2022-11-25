package com.example.carpark.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parking_spaces")
@Data
@NoArgsConstructor
public class ParkingSpace extends BaseEntity {
    private BigDecimal price;

    @OneToOne
    private Address address;

    @OneToOne
    private Ticket ticket;

    @OneToOne
    private Car car;
}
