package ru.yastrebov.rest.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yastrebov.rest.warehouse.entity.Item;
import ru.yastrebov.rest.warehouse.entity.Location;
import ru.yastrebov.rest.warehouse.repository.ItemService;
import ru.yastrebov.rest.warehouse.repository.LocationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(expected_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.calculate(item, location, new Date(),  date);
    }

    public double calculate(Item item,Location location, Date today, Date date) {

        long noOfDaysBetween = ChronoUnit.DAYS.between(today.toInstant(), date.toInstant());

        double insuranceAmount = item.getValue() * 0.1;
        double storageAmount;

        if (item.getVolume() < 1000 && !item.getArt()) {
            storageAmount = location.getRatemin();
        }
        else   {
            storageAmount = location.getRatemax();
        }

        double total = (storageAmount + insuranceAmount) * (noOfDaysBetween) + ADMIN_TAX;

        System.out.println("количество дней: "+ noOfDaysBetween);
        System.out.println("insuranceAmount: "+insuranceAmount);
        System.out.println("storageAmount: "+storageAmount);
        System.out.println("ADMIN_TAX: "+ADMIN_TAX);
        System.out.println("value: "+item.getValue());
        System.out.println("volume: "+item.getVolume());
        System.out.println("Art: "+ item.getArt());
        System.out.println("Total: " + total);

        return total;
    }
}
