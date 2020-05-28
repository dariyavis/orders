package ru.yastrebov.rest.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.yastrebov.rest.warehouse.repository.ItemService;
import ru.yastrebov.rest.warehouse.repository.ItemServiceImpl;

@Service
public class CalculateService {

    private final Integer ADMIN_TAX = 100;

    @Autowired
    private ItemService itemService;

    public double calculate(Integer itemId, Integer storeId, String expected_release) {
        System.out.println("Calculate smth...");
        System.out.println(itemService.read(itemId));
        return 0;
    }
}
