package com.isaakkrut.deliveryapp.testconfig;


import com.isaakkrut.deliveryapp.web.config.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@RequiredArgsConstructor
@TestConfiguration
public class SecurityTestConfig {

    private final PasswordEncoder passwordEncoder;


    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .username("admin")
                .password(passwordEncoder.encode("user"))
                .roles(UserRole.USER.name())
                .build();

        UserDetails admin = User.builder()
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(UserRole.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(Arrays.asList(
                user, admin
        ));
    }
}
