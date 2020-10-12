package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;
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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setViewResolvers(viewResolver)
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
    void getMenu() throws Exception {
        //when
        when(itemService.findAll()).thenReturn(new HashSet<>());
        when(categoryService.findAll()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categoriesDTO"))
                .andExpect(view().name("menu"));

        verify(itemService, times(1)).findAll();
        verify(categoryService, times(1)).findAll();
    }

    @Test
    void addItemToTheCart() throws Exception {
        Order order = mock(Order.class);
        //when
        when(itemService.findById(anyLong())).thenReturn(Item.builder().id(1L).build());

        //then
        mockMvc.perform(post("/order/items/1").sessionAttr("order", order))
                .andExpect(status().is3xxRedirection());

        verify(order).addItem(any());
    }

    @Test
    void deleteFromCart() throws Exception{
        Order order = mock(Order.class);
        mockMvc.perform(get("/order/items/delete/1").sessionAttr("order", order))
                .andExpect(status().is3xxRedirection());

        verify(order).deleteItemById(anyLong());
    }

    @Test
    void getCheckoutPageSignedInUser() throws Exception{
        User user = mock(User.class);

        //when
        when(user.isEmpty()).thenReturn(false);

        //then
        mockMvc.perform(get("/checkout").sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"));

        verify(user).isEmpty();

    }

    @Test
    void getCheckoutPageNoUser() throws Exception{
        User user = mock(User.class);

        //when
        when(user.isEmpty()).thenReturn(true);
        mockMvc.perform(get("/checkout").sessionAttr("user", user))
                .andExpect(status().is3xxRedirection());
        verify(user).isEmpty();
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