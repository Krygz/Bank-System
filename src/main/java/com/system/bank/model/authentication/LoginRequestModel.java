package com.system.bank.model.authentication;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestModel {
    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 5,max = 32, message = "password size should be between 5 and 32 digit or character")
    private String password;
}
