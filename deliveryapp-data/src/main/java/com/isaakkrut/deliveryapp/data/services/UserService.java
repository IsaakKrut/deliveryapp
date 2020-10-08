package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;

public interface UserService extends CrudService<User, Long>{
    public User getUserByEmail(String email);
}
