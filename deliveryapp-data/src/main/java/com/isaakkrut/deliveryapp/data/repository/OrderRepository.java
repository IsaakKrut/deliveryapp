package com.isaakkrut.deliveryapp.data.repository;

import com.isaakkrut.deliveryapp.data.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Set<Order> findAllByEmail(String email);
}
