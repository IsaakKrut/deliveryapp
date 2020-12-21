package com.isaakkrut.deliveryapp.security.domain;

import com.isaakkrut.deliveryapp.web.config.UserRole;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SecurityUserEntity user;

    private UserRole role;

    public Role(UserRole userRole) {
        this.role = userRole;
    }
}
