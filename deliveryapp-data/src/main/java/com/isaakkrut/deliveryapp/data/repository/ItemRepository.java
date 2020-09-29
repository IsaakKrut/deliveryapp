package com.isaakkrut.deliveryapp.data.repository;

import com.isaakkrut.deliveryapp.data.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
