package com.isaakkrut.deliveryapp.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityUserService {
    UserDetails selectUserByUsername(String username);
}
