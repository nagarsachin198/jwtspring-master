package com.example.jwtspring.springjwt.Response;


import com.example.jwtspring.springjwt.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {

    private String username;
    private String email;
    private String mobile;
    private Role role;
}
