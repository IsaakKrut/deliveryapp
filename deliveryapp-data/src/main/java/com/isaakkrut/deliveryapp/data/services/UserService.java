package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.User;

public interface UserService {
    public User getUserByEmail(String email);
}
