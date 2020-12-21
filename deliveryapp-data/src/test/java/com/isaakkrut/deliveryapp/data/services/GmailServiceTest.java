package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GmailServiceTest {

    @Mock
    JavaMailSender mailSender;

    @InjectMocks
    GmailService service;

    Order order;

    User user;

    @BeforeEach
    void setUp() {
        order = new Order();
        user = new User();
    }

    @Test
    void sendOrderConfirmation() {
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        service.sendOrderConfirmation(order);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void welcomeEmail() {
       // service.welcomeEmail(user);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void deleteAccountEmail() {
        service.deleteAccountEmail("emailString");
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}