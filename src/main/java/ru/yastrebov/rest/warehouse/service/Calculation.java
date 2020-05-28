package ru.yastrebov.rest.warehouse.service;

import java.util.Date;

public class Calculation {

    Integer totalAmount;
    Integer storageAmount;
    Integer insuraneAmount;
    Date date;


    Integer admiTax = 100;

    public Integer getTotalAmount() {
        totalAmount = (storageAmount + insuraneAmount)*date + admiTax;
    }
}
