package com.isaakkrut.deliveryapp.data.services.impl;

import com.isaakkrut.deliveryapp.data.domain.Category;
import com.isaakkrut.deliveryapp.data.repository.CategoryRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        //given
        Set<Category> categories = new HashSet<>();

        Category category1 = new Category();
        category1.setId(1L);
        Category category2 = new Category();
        category2.setId(2L);

        categories.add(category1);
        categories.add(category2);

        //when

        when(categoryRepository.findAll()).thenReturn(categories);

        //then
        Set<Category> returnedCategories = service.findAll();

        assertEquals(2, returnedCategories.size());
    }

    @Test
    void findById() {
        //given
        Category category = new Category();
        category.setId(1L);

        //when
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        //then
        Category returnedCategory = service.findById(1L);
        assertEquals(1L, returnedCategory.getId());


    }
}