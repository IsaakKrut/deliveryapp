package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.Category;
import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemJpaServiceTest {
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemJpaService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        //given
        Set<Item> items = new HashSet<>();

        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(2L);

        items.add(item1);
        items.add(item2);

        //when

        when(itemRepository.findAll()).thenReturn(items);

        //then
        Set<Item> returnedItems = service.findAll();

        assertEquals(2, returnedItems.size());
    }

    @Test
    void findById() {
        //given
        Item item = new Item();
        item.setId(1L);

        //when
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(item));

        //then
        Item returnedItem = service.findById(1L);
        assertEquals(item.getId(), returnedItem.getId());
    }

    @Test
    @Disabled
    void findAllByCategory(){
        //given
        Set<Item> items = new HashSet<>();

        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(2L);

        items.add(item1);
        items.add(item2);

        //when
       // when(itemRepository.findAllByCategory(any())).thenReturn(items);

        //then
       // Set<Item> returnedItems = service.findAllByCategory(new Category());
      //  assertEquals(2, returnedItems.size());
    }
}