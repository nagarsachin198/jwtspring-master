package com.example.jwtspring.springjwt.Response;

import com.example.jwtspring.springjwt.Model.User;
import lombok.*;

@Data
@Builder
public class JwtResponse {

    private String jwt;
    private User user;

}
