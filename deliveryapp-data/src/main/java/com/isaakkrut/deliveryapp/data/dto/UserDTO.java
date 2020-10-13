package com.isaakkrut.deliveryapp.data.dto;

import com.isaakkrut.deliveryapp.data.domain.Base;
import com.isaakkrut.deliveryapp.data.domain.Order;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends Base {

    @Builder
    public UserDTO(Long id, String dtoEmail, String dtoPassword, String dtoFirstName, String dtoLastName, Date dtoBirthDate) {
        super(id);
        this.dtoEmail = dtoEmail;
        this.dtoPassword = dtoPassword;
        this.dtoFirstName = dtoFirstName;
        this.dtoLastName = dtoLastName;
        this.dtoBirthDate = dtoBirthDate;
    }



    @NotBlank
    @Email
    private String dtoEmail;

    @NotBlank
    @Size(min=8, max=20)
    private String dtoPassword;


    @NotBlank
    @Size(min=2, max=30)
    private String dtoFirstName;


    @NotBlank
    @Size(min=2, max=30)
    private String dtoLastName;


    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dtoBirthDate;

    Set<Order> orders = new HashSet<>();

    public void addOrder(Order order){
        orders.add(order);
    }
}
