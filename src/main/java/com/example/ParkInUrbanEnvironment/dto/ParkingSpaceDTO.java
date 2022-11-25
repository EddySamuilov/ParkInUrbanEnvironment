package com.example.carpark.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ParkingSpaceDTO {
    private BigDecimal price;
    private AddressDTO address;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TicketDTO ticket;
}
