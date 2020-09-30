package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.OrderItem;
import com.isaakkrut.deliveryapp.data.repository.OrderItemRepository;
import com.isaakkrut.deliveryapp.data.services.OrderItemService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderItemJpaServiceTest {
    @Mock
    OrderItemRepository orderItemRepository;

    @InjectMocks
    OrderItemJpaService service;

    OrderItem orderItem;
    Set<OrderItem> orderItems;

    @BeforeEach
    void setUp() {
        orderItem = new OrderItem();
        orderItem.setId(1L);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(2L);

        orderItems = new HashSet<>();
        orderItems.add(orderItem);
        orderItems.add(orderItem1);
    }


    @Test
    void getItemsForOrder() {
        //when
        when(orderItemRepository.findAllByOrder(any())).thenReturn(orderItems);

        //then
        Set<OrderItem> returnedItems = service.getItemsForOrder(new Order());

        assertEquals(2, returnedItems.size());
        verify(orderItemRepository).findAllByOrder(any());
    }

    @Test
    void getOrdersForItem() {
        //when
        when(orderItemRepository.findAllByItem(any())).thenReturn(orderItems);

        //then
        Set<OrderItem> returnedItems = service.getOrdersForItem(new Item());

        assertEquals(2, returnedItems.size());
        verify(orderItemRepository).findAllByItem(any());
    }

    @Test
    void findAll() {
        //when
        when(orderItemRepository.findAll()).thenReturn(orderItems);

        //then
        Set<OrderItem> returnedItems = service.findAll();

        assertEquals(2, returnedItems.size());
        verify(orderItemRepository).findAll();
    }

    @Test
    void findById() {
        //when
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.of(orderItem));

        //then
        OrderItem returnedItem = service.findById(1L);

        assertEquals(1L, orderItem.getId());
        verify(orderItemRepository).findById(1L);
    }

    @Test
    void save() {
        //when
        when(orderItemRepository.save(any())).thenReturn(orderItem);

        //then
        OrderItem returnedItem = service.save(orderItem);

        assertEquals(orderItem.getId(), returnedItem.getId());
        verify(orderItemRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(orderItem);
        verify(orderItemRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(orderItemRepository).deleteById(anyLong());
    }
}