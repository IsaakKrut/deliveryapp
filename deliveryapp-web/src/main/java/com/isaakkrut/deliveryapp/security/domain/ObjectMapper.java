package com.isaakkrut.deliveryapp.security.domain;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectMapper {
    public static ApplicationUser userEntityToApplicationUser(SecurityUserEntity user){
        Set<GrantedAuthority> authorities= user.getRoles().stream()
                .map(role -> role.getRole().getGrantedAuthorities())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        return new ApplicationUser(
                user.getUsername(),
                user.getPassword(),
                authorities,
                true,
                !user.isLocked(),
                true,
                user.isEnabled()
        );
    }
}
