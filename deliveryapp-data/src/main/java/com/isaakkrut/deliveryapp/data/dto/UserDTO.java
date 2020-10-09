package com.isaakkrut.deliveryapp.data.dto;

import com.isaakkrut.deliveryapp.data.domain.Base;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends Base {

    @Builder
    public UserDTO(Long id,String dtoEmail,String dtoPassword,String dtoFirstName,String dtoLastName,LocalDate dtoBirthDate) {
        super(id);
        this.dtoEmail = dtoEmail;
        this.dtoPassword = dtoPassword;
        this.dtoFirstName = dtoFirstName;
        this.dtoLastName = dtoLastName;
        this.dtoBirthDate = dtoBirthDate;
    }



    @NotBlank
    @NotNull
    private String dtoEmail;

    @NotBlank
    @NotNull
    private String dtoPassword;


    @NotBlank
    private String dtoFirstName;


    @NotBlank
    private String dtoLastName;


    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtoBirthDate;
}
