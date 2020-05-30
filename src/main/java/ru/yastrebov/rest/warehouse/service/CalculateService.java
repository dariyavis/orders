package ru.yastrebov.rest.warehouse.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${property.admin_tax}")
    private Integer ADMIN_TAX;
    @Value("${property.store_id_default}")
    private Integer STORE_ID_DEFAULT;
    @Value("${property.volume_level}")
    private Integer VOLUME_LEVEL;

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
        return this.calculate(item, location, noOfDaysBetween);
    }

    public double calculate(Item item,Location location, long noOfDaysBetween) {

        double insuranceAmount = item.getValue() * 0.1;
        double storageAmount;

        if (item.getVolume() < VOLUME_LEVEL && !item.getArt()) {
            storageAmount = location.getRatemin();
        }
        else   {
            storageAmount = location.getRatemax();
        }

        double total = Precision.round(
                (storageAmount + insuranceAmount) * (noOfDaysBetween) + ADMIN_TAX,
                2);

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
