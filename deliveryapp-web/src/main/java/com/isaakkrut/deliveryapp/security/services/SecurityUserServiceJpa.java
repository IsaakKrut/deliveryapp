package com.isaakkrut.deliveryapp.security.services;

import com.isaakkrut.deliveryapp.security.domain.ObjectMapper;
import com.isaakkrut.deliveryapp.security.domain.SecurityUserEntity;
import com.isaakkrut.deliveryapp.security.repositories.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SecurityUserServiceJpa implements SecurityUserService {
    private final SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails selectUserByUsername(String username) {
        Optional<SecurityUserEntity> userEntityOptional= securityUserRepository.findByUsername(username);
        if (userEntityOptional.isPresent()){
            return ObjectMapper.userEntityToApplicationUser(userEntityOptional.get());
        } else{
            return null;
        }
    }
}
