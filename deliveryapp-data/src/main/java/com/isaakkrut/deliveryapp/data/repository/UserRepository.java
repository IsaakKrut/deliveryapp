package com.isaakkrut.deliveryapp.data.repository;

import com.isaakkrut.deliveryapp.data.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
