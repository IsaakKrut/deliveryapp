package com.isaakkrut.deliveryapp.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserAlreadyExistsException extends Throwable {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
