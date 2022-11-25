package com.example.carpark.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    private String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }

    private Instant created;

    private Instant modified;
}
