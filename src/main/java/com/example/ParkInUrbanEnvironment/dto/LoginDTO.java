package com.example.carpark.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

    @NotBlank
    @Size(min = 3, message = "Username must be more than 3 symbols")
    private String username;

    @NotBlank
    @Size(min = 3, message = "Password length must be at least 3 symbols!")
    private String password;
}
