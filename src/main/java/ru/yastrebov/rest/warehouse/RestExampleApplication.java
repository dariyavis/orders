package ru.yastrebov.rest.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.yastrebov.rest.warehouse.entity.Item;
import ru.yastrebov.rest.warehouse.service.CalculateService;

@SpringBootApplication
public class RestExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestExampleApplication.class, args);

    }

}
