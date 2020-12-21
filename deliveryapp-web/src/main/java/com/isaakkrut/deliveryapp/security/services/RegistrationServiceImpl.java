package com.isaakkrut.deliveryapp.security.services;

import com.isaakkrut.deliveryapp.data.converters.UserConverter;
import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import com.isaakkrut.deliveryapp.data.services.UserService;
import com.isaakkrut.deliveryapp.security.UserAlreadyExistsException;
import com.isaakkrut.deliveryapp.security.domain.Role;
import com.isaakkrut.deliveryapp.security.domain.SecurityUserEntity;
import com.isaakkrut.deliveryapp.web.config.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private static int CREDENTIALS_EXPIRY_DATE_MONTHS = 6;
    private static int ACCOUNT_EXPIRY_DATE_MONTHS = 36;

    private final UserService userService;
    private final RoleService roleService;
    private final SecurityUserService securityUserService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registerUser(UserDTO userDTO) throws UserAlreadyExistsException {
        UserDetails userDetails = securityUserService.selectUserByUsername(userDTO.getDtoEmail());
        User user = userService.getUserByEmail(userDTO.getDtoEmail());
        if (userDetails!= null || user!= null) {
            throw new UserAlreadyExistsException("User " + userDTO.getDtoEmail() + " already exists");
        }

        Set<Role> userRoles = new HashSet<Role>(Arrays.asList(new Role(UserRole.USER)));

        SecurityUserEntity userEntity = SecurityUserEntity.builder()
                .username(userDTO.getDtoEmail())
                .password(passwordEncoder.encode(userDTO.getDtoPassword()))
                .isEnabled(true)
                .isLocked(false)
                .credentialsExpiryDate(LocalDate.now().plusMonths(CREDENTIALS_EXPIRY_DATE_MONTHS))
                .expiryDate(LocalDate.now().plusMonths(ACCOUNT_EXPIRY_DATE_MONTHS))
                .build();

        userEntity.setRoles(userRoles);

        roleService.saveAll(userRoles);
        securityUserService.registerUser(userEntity);
        userService.save(UserConverter.userDtoToUser(userDTO));
    }
}
