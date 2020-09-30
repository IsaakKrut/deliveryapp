package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.OrderItem;

import java.util.Set;

public interface OrderItemService extends CrudService<OrderItem, Long> {
    Set<OrderItem> getItemsForOrder(Order order);
    Set<OrderItem> getOrdersForItem(Item item);
}
