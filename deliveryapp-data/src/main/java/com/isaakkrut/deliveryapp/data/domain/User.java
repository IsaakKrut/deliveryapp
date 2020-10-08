package com.isaakkrut.deliveryapp.data.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User extends Base {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}