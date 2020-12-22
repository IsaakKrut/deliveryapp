package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.services.OrderService;
import com.isaakkrut.deliveryapp.data.services.UserService;
import com.isaakkrut.deliveryapp.testconfig.SecurityTestConfig;
import com.isaakkrut.deliveryapp.web.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    UserService userService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    UserDetailsService userDetailsService;

    @WithMockUser(roles = "ADMIN")
    @Test
    void getHomepageAuthSuccess() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/home"));
    }

    @WithMockUser(roles = "USER")
    @Test
    void getHomepageAuthFailure() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().is4xxClientError());

    }

    @WithMockUser( roles = "ADMIN")
    @Test
    void getAllOrdersAuthSuccess() throws Exception {
        mockMvc.perform(get("/admin/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/orders"));
    }

    @WithMockUser( roles = "USER")
    @Test
    void getAllOrdersAuthFailure() throws Exception {
        mockMvc.perform(get("/admin/orders"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser( roles = "ADMIN")
    @Test
    void getAllUsersAuthSuccess() throws Exception {
        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/users"));
    }

    @WithMockUser( roles = "USER")
    @Test
    void getAllUsersAuthFailure() throws Exception {
        mockMvc.perform(get("/admin/users"))
                .andExpect(status().is4xxClientError());
    }
}