package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.OrderItem;
import com.isaakkrut.deliveryapp.data.repository.OrderItemRepository;
import com.isaakkrut.deliveryapp.data.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderJpaServiceTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderItemRepository orderItemRepository;

    @InjectMocks
    OrderJpaService service;

    Order order;
    Set<Order> orders = new HashSet<>();

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1L);
        Order order2 = new Order();
        order2.setId(2L);

        orders.add(order2);
        orders.add(order);
    }

    @AfterEach
    void tearDown() {
        orders = new HashSet<>();
        order = null;
    }

    @Test
    void getOrdersByEmail() {
        //when
        when(orderRepository.findAllByEmail(anyString())).thenReturn(orders);
        when(orderItemRepository.findAllByOrder(any())).thenReturn(new HashSet<>());

        //then
        Set<Order> returnedOrders = service.getOrdersByEmail("ok@gmail.com");
        assertEquals(2, returnedOrders.size());
        verify(orderRepository).findAllByEmail(anyString());
    }

    @Test
    void findAll() {
        //when
        when(orderRepository.findAll()).thenReturn(orders);

        //then
        Set<Order> returnedOrders = service.findAll();
        assertEquals(2, returnedOrders.size());
        verify(orderRepository).findAll();
    }

    @Test
    void findById() {
        //when
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        //then
        Order returnedOrder = service.findById(1L);

        assertEquals(1L, returnedOrder.getId());
        verify(orderRepository).findById(anyLong());
    }

    @Test
    void save() {
        //when
        when(orderRepository.save(any())).thenReturn(order);

        //then
        Order savedOrder = service.save(order);
        assertEquals(order.getId(), savedOrder.getId());
        verify(orderRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(order);
        verify(orderRepository).delete(order);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(orderRepository).deleteById(1L);
    }
}