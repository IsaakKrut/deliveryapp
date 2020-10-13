package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.OrderItem;
import com.isaakkrut.deliveryapp.data.repository.OrderItemRepository;
import com.isaakkrut.deliveryapp.data.repository.OrderRepository;
import com.isaakkrut.deliveryapp.data.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderJpaService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderJpaService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Set<Order> getOrdersByEmail(String email) {
        Set<Order> orders = orderRepository.findAllByEmail(email);
        orders.forEach(order ->{
            Set<OrderItem> items = orderItemRepository.findAllByOrder(order);
            items.forEach(order::addOrderItem);
        });

        return orders;
    }

    @Override
    public Set<Order> findAll() {
        Set<Order> orders = new HashSet<>();
         orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order findById(Long aLong) {
        return orderRepository.findById(aLong).orElse(null);
    }

    @Override
    public Order save(Order object) {
        Order orderToSave = Order.builder().orderDate(object.getOrderDate()).email(object.getEmail()).totalPrice(object.getTotalPrice()).build();
        Order savedOrder = orderRepository.save(orderToSave);
        object.getItems().forEach(item->{
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        });
        return savedOrder;
    }

    @Override
    public void delete(Order object) {
        orderRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }
}
