package com.isaakkrut.deliveryapp.data.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name="users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base {

    @Builder
    public User(Long id, String email, String password, String firstName, String lastName, LocalDate birthDate) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public void clear(){
        this.setId(null);
        this.email = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.birthDate = null;
    }
}