package com.example.jwtspring.springjwt.Request;


import com.example.jwtspring.springjwt.Model.Role;
import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    @Min(8)
    private String password;
    @NotNull
    private Role role ;
    @NotNull
    private String mobile;

}
