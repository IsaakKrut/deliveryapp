package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.OrderItem;
import com.isaakkrut.deliveryapp.data.repository.OrderItemRepository;
import com.isaakkrut.deliveryapp.data.services.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderItemJpaService implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemJpaService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Set<OrderItem> getItemsForOrder(Order order) {
        Set<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);
        return orderItems;
    }

    @Override
    public Set<OrderItem> getOrdersForItem(Item item) {
        Set<OrderItem> orderItems = orderItemRepository.findAllByItem(item);
        return orderItems;
    }

    @Override
    public Set<OrderItem> findAll() {
        Set<OrderItem> orderItems = new HashSet<>();
        orderItemRepository.findAll().forEach(orderItems::add);
        return orderItems;
    }

    @Override
    public OrderItem findById(Long aLong) {
        return orderItemRepository.findById(aLong).orElse(null);
    }

    @Override
    public OrderItem save(OrderItem object) {
        return orderItemRepository.save(object);
    }

    @Override
    public void delete(OrderItem object) {
        orderItemRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        orderItemRepository.deleteById(aLong);
    }
}
