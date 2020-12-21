package com.isaakkrut.deliveryapp.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final SecurityUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userService.selectUserByUsername(username);
        if (userDetails == null){
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return userDetails;
    }
}
