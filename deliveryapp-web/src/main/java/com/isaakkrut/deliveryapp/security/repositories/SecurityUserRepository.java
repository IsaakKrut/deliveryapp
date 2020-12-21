package com.isaakkrut.deliveryapp.security.repositories;

import com.isaakkrut.deliveryapp.security.domain.SecurityUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SecurityUserRepository extends CrudRepository<SecurityUserEntity, Long> {
    Optional<SecurityUserEntity> findByUsername(String username);
}
