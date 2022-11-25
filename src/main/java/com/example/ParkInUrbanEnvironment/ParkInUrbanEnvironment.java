package com.example.carpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ParkInUrbanEnvironment {

    public static void main(String[] args) {
        SpringApplication.run(ParkInUrbanEnvironment.class, args);
    }

}
