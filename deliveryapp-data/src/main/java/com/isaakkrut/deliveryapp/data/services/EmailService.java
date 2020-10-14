package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;

public interface EmailService {
    public void sendOrderConfirmation(Order order);
}
