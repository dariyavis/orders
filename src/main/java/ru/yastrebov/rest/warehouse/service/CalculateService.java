package ru.yastrebov.rest.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.yastrebov.rest.warehouse.entity.Item;
import ru.yastrebov.rest.warehouse.repository.ItemRepository;
import ru.yastrebov.rest.warehouse.repository.ItemService;
import ru.yastrebov.rest.warehouse.repository.ItemServiceImpl;
import ru.yastrebov.rest.warehouse.repository.LocationService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CalculateService {

    private final Integer ADMIN_TAX = 100;


    @Autowired
    private ItemService itemService;
    @Autowired
    private LocationService locationService;

    public double calculate(Integer itemId, Integer storeId, String expected_release) {

       double insuranceAmount = itemService.read(itemId).getValue() * 0.1;
       double storageAmount;

        if ((itemService.read(itemId).getVolume() < 1000) && itemService.read(itemId).getArt()==false) {
              storageAmount = locationService.read(storeId).getRatemin();
        }
        else   {
              storageAmount = locationService.read(storeId).getRatemax();
        }

        String dateBeforeString = "2020-05-29";
        String dateAfterString = expected_release;

        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

       double total = (storageAmount + insuranceAmount) * (noOfDaysBetween) + ADMIN_TAX;
        System.out.println("количество дней: "+ noOfDaysBetween);
        System.out.println("insuranceAmount: "+insuranceAmount);
        System.out.println("storageAmount: "+storageAmount);
        System.out.println("ADMIN_TAX: "+ADMIN_TAX);
        System.out.println("value: "+itemService.read(itemId).getValue());
        System.out.println("volume: "+itemService.read(itemId).getVolume());
        System.out.println("Art: "+ itemService.read(itemId).getArt());
        System.out.println("Total: " + total);


        return total;
    }
}
//http://localhost:8080/invoice-preview?itemId=1&storeId=1&expected_release=2020-06-06
//jobId=1
//storeId=1
//expected_release = 2020-06-06
//количество дней: 8
//insuranceAmount: 11.100000000000001
//storageAmount: 2000.0
//ADMIN_TAX: 100
//value: 111
//volume: 11
//Art: true
//Total: 16188.8