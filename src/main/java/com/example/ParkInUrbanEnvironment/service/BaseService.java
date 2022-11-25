package com.example.carpark.service;

import javassist.NotFoundException;

import java.util.Collection;

public interface BaseService<V> {
    Collection<V> getAll();

    V create(V model);

    V findById(String id) throws NotFoundException;

    boolean remove(String id);

    V update(String id, V viewDto) throws NotFoundException;

    V getByName(String name) throws NotFoundException;
}