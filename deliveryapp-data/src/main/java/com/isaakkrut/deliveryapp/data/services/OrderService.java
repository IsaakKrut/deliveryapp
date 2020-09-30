package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;

import java.util.Set;

public interface OrderService extends CrudService<Order, Long> {
    Set<Order> getOrdersByEmail(String email);
}
