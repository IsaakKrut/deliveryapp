package com.isaakkrut.deliveryapp.security.domain;

import com.isaakkrut.deliveryapp.web.config.UserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecurityUserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    private LocalDate expiryDate;
    private boolean isLocked;
    private LocalDate credentialsExpiryDate;
    private boolean isEnabled;

    public void setRoles(Set<Role> roles){
        this.roles = roles;
        roles.forEach(role -> role.setUser(this));
    }

}

