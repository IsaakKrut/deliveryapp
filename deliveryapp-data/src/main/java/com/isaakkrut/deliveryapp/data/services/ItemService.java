package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.repository.ItemRepository;

import java.util.HashSet;
import java.util.Set;

public class ItemService implements ReadOnlyService<Item, Long> {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
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
