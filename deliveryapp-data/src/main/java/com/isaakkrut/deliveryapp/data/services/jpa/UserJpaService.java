package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.repository.UserRepository;
import com.isaakkrut.deliveryapp.data.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserJpaService implements UserService{
    private final UserRepository userRepository;

    public UserJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
