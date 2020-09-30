package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.repository.ItemRepository;
import com.isaakkrut.deliveryapp.data.services.ItemService;

import java.util.HashSet;
import java.util.Set;

public class ItemJpaService implements ItemService {

    private final ItemRepository itemRepository;

    public ItemJpaService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> findAll() {
        Set<Item> items = new HashSet<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public Item findById(Long aLong) {
        return itemRepository.findById(aLong).orElse(null);
    }
}
