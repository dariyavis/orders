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
    private Integer admin_tax;
    @Value("${property.store_id_default}")
    private Integer store_id_default;
    @Value("${property.volume_level}")
    private Integer volume_level;

    @Autowired
    private ItemService itemService;

    @Autowired
    private LocationService locationService;

    public double calculate(Integer itemId, Integer storeId, String expected_release) {
        System.out.println("Calculate smth...");
        Item item = itemService.read(itemId);
        Location location = storeId == null ? locationService.read(store_id_default) : locationService.read(storeId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(expected_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long noOfDaysBetween = ChronoUnit.DAYS.between(new Date().toInstant(), date.toInstant());
        return this.calculate(item, location, noOfDaysBetween, admin_tax, volume_level);

    }
    public double calculate(Item item, Location location, long noOfDaysBetween, Integer admin_tax, Integer volume_level) {

        double insuranceAmount = item.getValue() * 0.1;
        double storageAmount;

        if (item.getVolume() < volume_level && !item.getArt()) {
            storageAmount = location.getRatemin();
        } else {
            storageAmount = location.getRatemax();
        }

        double total = Precision.round(
                (storageAmount + insuranceAmount) * (noOfDaysBetween) + admin_tax,
                2);

        System.out.println("количество дней: " + noOfDaysBetween);
        System.out.println("insuranceAmount: " + insuranceAmount);
        System.out.println("storageAmount: " + storageAmount);
        System.out.println("ADMIN_TAX: " + admin_tax);
        System.out.println("value: " + item.getValue());
        System.out.println("volume: " + item.getVolume());
        System.out.println("Art: " + item.getArt());
        System.out.println("Total: " + total);

        return total;
    }
}
