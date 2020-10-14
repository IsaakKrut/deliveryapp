package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Login;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import com.isaakkrut.deliveryapp.data.services.CategoryService;
import com.isaakkrut.deliveryapp.data.services.EmailService;
import com.isaakkrut.deliveryapp.data.services.ItemService;
import com.isaakkrut.deliveryapp.data.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
    @Mock
    EmailService emailService;

    @InjectMocks
    IndexController controller;

    MockMvc mockMvc;

    User mockUser;
    Order mockOrder;

    @BeforeEach
    void setUp() {
        mockUser = mock(User.class);
        mockOrder = mock(Order.class);

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
        mockUser = null;
        mockOrder = null;
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
        //when
        when(itemService.findById(anyLong())).thenReturn(Item.builder().id(1L).build());

        //then
        mockMvc.perform(post("/order/items/1").sessionAttr("order", mockOrder))
                .andExpect(status().is3xxRedirection());

        verify(mockOrder).addItem(any());
    }

    @Test
    void deleteFromCart() throws Exception{
        mockMvc.perform(get("/order/items/delete/1").sessionAttr("order", mockOrder))
                .andExpect(status().is3xxRedirection());

        verify(mockOrder).deleteItemById(anyLong());
    }

    @Test
    void getCheckoutPageSignedInUser() throws Exception{
        //when
        when(mockUser.isEmpty()).thenReturn(false);

        //then
        mockMvc.perform(get("/checkout").sessionAttr("user", mockUser))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"));

        verify(mockUser).isEmpty();

    }

    @Test
    void getCheckoutPageNoUser() throws Exception{

        //when
        when(mockUser.isEmpty()).thenReturn(true);
        mockMvc.perform(get("/checkout").sessionAttr("user", mockUser))
                .andExpect(status().is3xxRedirection());
        verify(mockUser).isEmpty();
    }

    @Test
    void getLoginPage() throws Exception {

        mockMvc.perform(get("/signin"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("login"))
                .andExpect(view().name("signin"));
    }

    @Test
    void signInSuccess() throws Exception {

        //when
        when(userService.validateUser(any())).thenReturn(true);
        when(userService.getUserByEmail(any())).thenReturn(new User());

        //then
        mockMvc.perform(post("/signin")
                .flashAttr("login", new Login())
                .sessionAttr("user", new User()))
                .andExpect(status().is3xxRedirection());

        verify(userService).validateUser(any());
    }
    @Test
    void signInFail() throws Exception{
        //when
        when(userService.validateUser(any())).thenReturn(false);

        mockMvc.perform(post("/signin")
                .flashAttr("login", new Login())
                .sessionAttr("user", new User()))
            .andExpect(status().isOk())
            .andExpect(view().name("signin"));
        verify(userService).validateUser(any());
    }

    @Test
    void signout() throws Exception{

        mockMvc.perform(get("/signout")
                .sessionAttr("user", mockUser))
                .andExpect(status().is3xxRedirection());
        verify(mockUser).clear();
    }

    @Test
    void getRegistrationPageNoSignedInUser() throws Exception{
        //when
        when(mockUser.isEmpty()).thenReturn(true);

        //then
        mockMvc.perform(get("/register")
                .sessionAttr("user", mockUser))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userDTO"))
                .andExpect(view().name("userform"));
    }

    @Test
    void getRegistrationPageSignedInUser() throws Exception{

        //when
        when(mockUser.isEmpty()).thenReturn(false);

        //then
        mockMvc.perform(get("/register")
                        .sessionAttr("user", mockUser))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    void registerUserFail() throws Exception{

        //then
        mockMvc.perform(post("/register")
                .flashAttr("userDTO", new UserDTO())
                .sessionAttr("user", new User()))
                .andExpect(view().name("userform"));

        verify(userService, times(0)).getUserByEmail(any());
        verify(userService, times(0)).save(any());
    }

    @Test
    void registerUserSuccess() throws Exception{

        //when
        when(userService.getUserByEmail(any())).thenReturn(null);
        when(userService.save(any())).thenReturn(new User());

        //then
        mockMvc.perform(post("/register")
                    .param("dtoEmail", "ik@gmail.com")
                    .param("dtoPassword", "12345678")
                    .param("dtoFirstName", "Isaak")
                    .param("dtoLastName", "Krut")
                    .param("dtoBirthDate", "1999-01-07")
                    .sessionAttr("user", new User()))
                .andExpect(status().is3xxRedirection());

        verify(userService, times(1)).save(any());
    }


    @Test
    void getAccountPage() {
    }
}