package com.isaakkrut.deliveryapp.security.repositories;

import com.isaakkrut.deliveryapp.security.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
