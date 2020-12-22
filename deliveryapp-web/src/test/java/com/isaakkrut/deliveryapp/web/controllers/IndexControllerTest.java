package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.converters.UserConverter;
import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Login;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import com.isaakkrut.deliveryapp.data.services.*;
import com.isaakkrut.deliveryapp.security.services.RegistrationService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    UserService userService;

    @MockBean
    ItemService itemService;

    @MockBean
    EmailService emailService;

    @MockBean
    CategoryService categoryService;

    @MockBean
    RegistrationService registrationService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    UserDetailsService userDetailsService;

    @MockBean
    UserConverter userConverter;

    User mockUser = mock(User.class);
    Order mockOrder = mock(Order.class);


    @Test
    void getIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists( "order"))
                .andExpect(view().name("index"));
    }

    @Test
    void getMenu() throws Exception {
        //when

        //then
        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categoriesDTO"))
                .andExpect(view().name("menu"));

        verify(itemService, times(1)).findAll();
        verify(categoryService, times(1)).findAll();
    }

    @WithMockUser(value = "spring")
    @Test
    void getCheckoutPageSignedInUser() throws Exception{

        //then
        mockMvc.perform(get("/checkout"))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"));


    }

    @Test
    void requestSignInAnonymousUser() throws Exception {

        mockMvc.perform(get("/signin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser
    @Test
    void requestSignInSignedInUser() throws Exception {

        mockMvc.perform(get("/signin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account"));
    }

    @WithAnonymousUser
    @Test
    void getRegistrationPageAnonymousInUser() throws Exception{

        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userDto"))
                .andExpect(view().name("userform"));
    }

    @WithMockUser
    @Test
    void getRegistrationPageSignedInUser() throws Exception{

        mockMvc.perform(get("/register"))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    void registerUserFail() throws Exception{

        //then
        mockMvc.perform(post("/register")
                .flashAttr("userDto", new UserDTO()))
                .andExpect(view().name("userform"));

        verify(userService, times(0)).getUserByEmail(any());
        verify(userService, times(0)).save(any());
    }

    @Test
    void registerUserSuccess() throws Exception{

        mockMvc.perform(post("/register")
                    .param("dtoEmail", "ik@gmail.com")
                    .param("dtoPassword", "12345678")
                    .param("dtoFirstName", "Isaak")
                    .param("dtoLastName", "Krut")
                    .param("dtoBirthDate", "1999-01-07"))
                .andExpect(status().is3xxRedirection());

    }


    @WithMockUser
    @Test
    void getAccountPageUser() throws Exception {
        when(orderService.getOrdersByEmail(any())).thenReturn(Sets.newSet());
        when(userConverter.userToUserDTO(any())).thenReturn(new UserDTO());
        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userDto"));
    }

    @WithAnonymousUser
    @Test
    void getAccountPageAnonymousUser() throws Exception {
        mockMvc.perform(get("/account"))
                .andExpect(status().is3xxRedirection());
    }
}