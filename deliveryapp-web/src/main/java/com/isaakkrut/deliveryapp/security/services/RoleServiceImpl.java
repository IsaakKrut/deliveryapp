package com.isaakkrut.deliveryapp.security.services;

import com.isaakkrut.deliveryapp.security.domain.Role;
import com.isaakkrut.deliveryapp.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void saveAll(Set<Role> role) {
        roleRepository.saveAll(role);
    }
}
