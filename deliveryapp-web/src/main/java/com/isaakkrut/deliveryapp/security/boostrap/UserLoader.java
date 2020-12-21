package com.isaakkrut.deliveryapp.security.boostrap;

import com.isaakkrut.deliveryapp.security.domain.Role;
import com.isaakkrut.deliveryapp.security.domain.SecurityUserEntity;
import com.isaakkrut.deliveryapp.security.repositories.RoleRepository;
import com.isaakkrut.deliveryapp.security.repositories.SecurityUserRepository;
import com.isaakkrut.deliveryapp.web.config.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class UserLoader implements CommandLineRunner {

    private final SecurityUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {


        if (userRepository.count() == 0) {
            SecurityUserEntity admin = userRepository.save(SecurityUserEntity.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .credentialsExpiryDate(LocalDate.now().plusMonths(3))
                    .expiryDate(LocalDate.now().plusMonths(24))
                    .isEnabled(true)
                    .isLocked(false).build());
            SecurityUserEntity creator = userRepository.save(SecurityUserEntity.builder()
                    .username("creator")
                    .password(passwordEncoder.encode("creator"))
                    .credentialsExpiryDate(LocalDate.now().plusMonths(3))
                    .expiryDate(LocalDate.now().plusMonths(24))
                    .isEnabled(true)
                    .isLocked(false).build());
            SecurityUserEntity user = userRepository.save(SecurityUserEntity.builder()
                    .username("krut.isaak@yandex.com")
                    .password(passwordEncoder.encode("password"))
                    .credentialsExpiryDate(LocalDate.now().plusMonths(3))
                    .expiryDate(LocalDate.now().plusMonths(24))
                    .isEnabled(true)
                    .isLocked(false).build());

            Set<Role> adminRoles = new HashSet<>(Arrays.asList(new Role(UserRole.ADMIN)));
            Set<Role> creatorRoles = new HashSet<>(Arrays.asList(new Role(UserRole.CREATOR)));
            Set<Role> userRoles = new HashSet<>(Arrays.asList(new Role(UserRole.USER)));


           admin.setRoles(adminRoles);
           creator.setRoles(creatorRoles);
           user.setRoles(userRoles);

            roleRepository.saveAll(adminRoles);
            roleRepository.saveAll(creatorRoles);
            roleRepository.saveAll(userRoles);

            userRepository.save(admin);
            userRepository.save(creator);
            userRepository.save(user);
        }
    }
}
