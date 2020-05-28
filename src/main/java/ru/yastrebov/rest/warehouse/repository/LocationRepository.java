package ru.yastrebov.rest.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yastrebov.rest.warehouse.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
