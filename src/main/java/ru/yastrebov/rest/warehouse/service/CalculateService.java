package ru.yastrebov.rest.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yastrebov.rest.warehouse.entity.Item;
import ru.yastrebov.rest.warehouse.entity.Location;
import ru.yastrebov.rest.warehouse.repository.ItemService;
import ru.yastrebov.rest.warehouse.repository.LocationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class CalculateService {

    private final Integer ADMIN_TAX = 100;
    private final Integer STORE_ID_DEFAULT = 1;

    @Autowired
    private ItemService itemService;

    @Autowired
    private LocationService locationService;

    public double calculate(Integer itemId, Integer storeId, String expected_release) {
        System.out.println("Calculate smth...");
        Item item = itemService.read(itemId);
        Location location = storeId == null?locationService.read(STORE_ID_DEFAULT):locationService.read(storeId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(expected_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.calculate(item, location, new Date(),  date);
    }

    public double calculate(Item item,Location location, Date today, Date date) {
        return 0;
    }
}
