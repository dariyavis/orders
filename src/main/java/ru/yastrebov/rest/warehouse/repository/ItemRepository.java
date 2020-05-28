package ru.yastrebov.rest.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yastrebov.rest.warehouse.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>  {
}
