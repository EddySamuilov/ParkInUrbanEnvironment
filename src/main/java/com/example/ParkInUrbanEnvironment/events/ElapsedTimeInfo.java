package com.example.carpark.events;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ElapsedTimeInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElapsedTimeInfo.class);

    private BigDecimal price;
    private int elapsedHours;

    public ElapsedTimeInfo() {
        this.price = new BigDecimal(0);
        elapsedHours = 1;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void showTicketInfo() {
        price = price.add(BigDecimal.valueOf(2));

        LOGGER.info("Your ticket price become: {}lv. You stayed {} hour/s", price, elapsedHours++);
    }
}
