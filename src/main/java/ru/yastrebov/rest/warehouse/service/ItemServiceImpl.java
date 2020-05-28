package ru.yastrebov.rest.warehouse.service;

import org.springframework.stereotype.Service;
import ru.yastrebov.rest.warehouse.entity.Item;
import ru.yastrebov.rest.warehouse.repository.ItemRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void create(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item>  readAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item read(int id) {
        return itemRepository.getOne(id);
    }

    @Override
    public boolean update(Item item, int id) {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            itemRepository.save(item);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}