package com.isaakkrut.deliveryapp.security.services;

import com.isaakkrut.deliveryapp.security.domain.Role;

import java.util.Set;

public interface RoleService {
    void saveAll(Set<Role> role);
}
