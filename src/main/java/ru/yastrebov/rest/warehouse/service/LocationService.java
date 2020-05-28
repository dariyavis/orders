package ru.yastrebov.rest.warehouse.service;

import ru.yastrebov.rest.warehouse.entity.Location;

import java.util.List;

public interface LocationService {

    void create(Location location);

    List<Location> readAll();

    Location read(int id);

    boolean update(Location location, int id);

    boolean delete(int id);
}
