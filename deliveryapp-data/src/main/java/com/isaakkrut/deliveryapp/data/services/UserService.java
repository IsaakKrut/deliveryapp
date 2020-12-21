package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Login;
import com.isaakkrut.deliveryapp.data.domain.User;

public interface UserService extends CrudService<User, Long>{
    User getUserByEmail(String email);

    void deleteUserByEmail(String username);
}
