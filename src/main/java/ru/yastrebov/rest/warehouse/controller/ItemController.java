package ru.yastrebov.rest.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yastrebov.rest.warehouse.entity.Item;
import ru.yastrebov.rest.warehouse.service.ItemService;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(value = "/items")
    public ResponseEntity<?> create(@RequestBody Item item) {
        itemService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/items")
    public ResponseEntity<List<Item>> read() {
        final List<Item> items = itemService.readAll();

        return items != null &&  !items.isEmpty()
                ? new ResponseEntity<>(items, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<Item> read(@PathVariable(name = "id") int id) {
        final Item item = itemService.read(id);

        return item != null
                ? new ResponseEntity<>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/items/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Item item) {
        final boolean updated = itemService.update(item, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/items/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = itemService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
