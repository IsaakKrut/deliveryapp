package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.services.CategoryService;
import com.isaakkrut.deliveryapp.data.services.ItemService;
import com.isaakkrut.deliveryapp.data.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    CategoryService categoryService;
    @Mock
    ItemService itemService;
    @Mock
    UserService userService;

    @InjectMocks
    IndexController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "order"))
                .andExpect(view().name("index"));
    }

    @Test
    void getMenu() {
    }

    @Test
    void addItemToTheCart() {
    }

    @Test
    void deleteFromCart() {
    }

    @Test
    void getCheckoutPage() {
    }

    @Test
    void getLoginPage() {
    }

    @Test
    void signIn() {
    }

    @Test
    void getRegistrationPage() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void signout() {
    }

    @Test
    void getAccountPage() {
    }
}