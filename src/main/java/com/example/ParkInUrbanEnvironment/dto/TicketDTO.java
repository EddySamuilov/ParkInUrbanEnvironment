package com.example.carpark.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@NoArgsConstructor
public class TicketDTO {
    private int number = new Random().nextInt(999999);
    private String dateAndTime;
}

