package ru.yastrebov.rest.warehouse.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateServiceTest {

    @Test
    void сalculate() {

        CalculateService calculateService = new CalculateService();
        double actual = calculateService.calculate(1, 1, "2020-06-06");
        double expected = 16188.8;

       assertEquals(expected, actual);
    }
}