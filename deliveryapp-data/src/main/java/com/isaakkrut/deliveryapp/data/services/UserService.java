package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Login;
import com.isaakkrut.deliveryapp.data.domain.User;

public interface UserService extends CrudService<User, Long>{
    public User getUserByEmail(String email);
    public Boolean validateUser(Login login);

    void deleteUserByEmail(String username);
}
