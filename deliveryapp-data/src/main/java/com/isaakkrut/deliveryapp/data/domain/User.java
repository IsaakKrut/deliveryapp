package com.isaakkrut.deliveryapp.data.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User extends Base {

    @Builder
    public User(Long id, String email, String firstName, String lastName, Date birthDate) {
        super(id);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    private String email;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

}