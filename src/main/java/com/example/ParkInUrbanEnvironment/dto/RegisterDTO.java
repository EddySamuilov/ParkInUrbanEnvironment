package com.example.carpark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @Size(min = 3, message = "Username must be more than 3 symbols")
    private String username;

    @Size(min = 3, message = "Password must be more than 3 symbols")
    private String password;
    private String confirmPassword;

    private String firstName;
    private String lastName;

    @Email
    private String email;

    private int age;
}
