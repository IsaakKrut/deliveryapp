package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;

public interface EmailService {
    public void sendOrderConfirmation(Order order);
    public void welcomeEmail(User user);
    public void deleteAccountEmail(String user);
}
