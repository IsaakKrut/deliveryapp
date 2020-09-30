package com.isaakkrut.deliveryapp.data.repository;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    Set<OrderItem> findAllByOrder(Order order);
    Set<OrderItem> findAllByItem(Item item);
}
