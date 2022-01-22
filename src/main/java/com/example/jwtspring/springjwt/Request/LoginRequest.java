package com.example.jwtspring.springjwt.Request;


import lombok.*;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotNull(message = "Please Enter Email")
    private String email;
    @NotNull(message = "Please Enter Password")
    private String password;

}
