package com.isaakkrut.deliveryapp.data.converters;

import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User userDtoToUser(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getDtoEmail())
                .firstName(userDTO.getDtoFirstName())
                .lastName(userDTO.getDtoLastName())
                .birthDate(userDTO.getDtoBirthDate())
                .build();
    }

    public UserDTO userToUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .dtoEmail(user.getEmail())
                .dtoFirstName(user.getFirstName())
                .dtoLastName(user.getLastName())
                .dtoBirthDate(user.getBirthDate())
                .build();
    }
}
