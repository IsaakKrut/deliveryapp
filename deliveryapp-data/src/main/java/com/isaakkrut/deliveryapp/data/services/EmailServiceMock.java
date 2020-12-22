package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("mock-email")
@Service
public class EmailServiceMock implements EmailService {
    @Override
    public void sendOrderConfirmation(Order order) {

    }

    @Override
    public void welcomeEmail(UserDTO user) {

    }

    @Override
    public void deleteAccountEmail(String user) {

    }
}
