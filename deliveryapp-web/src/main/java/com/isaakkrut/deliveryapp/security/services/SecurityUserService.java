package com.isaakkrut.deliveryapp.security.services;

import com.isaakkrut.deliveryapp.security.domain.SecurityUserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityUserService {
    UserDetails selectUserByUsername(String username);
    void registerUser(SecurityUserEntity securityUserEntity);
}
