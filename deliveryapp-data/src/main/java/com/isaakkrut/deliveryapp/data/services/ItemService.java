package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Category;
import com.isaakkrut.deliveryapp.data.domain.Item;

import java.util.Set;

public interface ItemService extends ReadOnlyService<Item, Long> {
    Set<Item> findAllByCategory(Category category);
}
