package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;

public interface EmailService {
    public void sendOrderConfirmation(Order order);
    public void welcomeEmail(UserDTO user);
    public void deleteAccountEmail(String user);
}
