package ru.yastrebov.rest.warehouse.service;

import ru.yastrebov.rest.warehouse.entity.Item;

import java.util.List;

public interface ItemService {

    void create(Item item);

    List<Item> readAll();

    Item read(int id);

    boolean update(Item item, int id);

    boolean delete(int id);
}
