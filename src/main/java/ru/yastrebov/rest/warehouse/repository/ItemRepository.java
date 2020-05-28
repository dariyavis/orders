package ru.yastrebov.rest.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yastrebov.rest.warehouse.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>  {
}
