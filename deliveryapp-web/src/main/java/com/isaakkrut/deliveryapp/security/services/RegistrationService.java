package com.isaakkrut.deliveryapp.security.services;

import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import com.isaakkrut.deliveryapp.security.UserAlreadyExistsException;

public interface RegistrationService {
    void registerUser(UserDTO userDTO) throws UserAlreadyExistsException;
}
