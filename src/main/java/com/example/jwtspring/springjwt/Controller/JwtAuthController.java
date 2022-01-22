package com.example.jwtspring.springjwt.Controller;


import com.example.jwtspring.springjwt.Exception.CustomException;
import com.example.jwtspring.springjwt.Model.UserDetailsImpl;
import com.example.jwtspring.springjwt.Request.LoginRequest;
import com.example.jwtspring.springjwt.Request.SignupRequest;
import com.example.jwtspring.springjwt.Response.JwtResponse;
import com.example.jwtspring.springjwt.Service.UserDetailsServiceImpl;
import com.example.jwtspring.springjwt.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Log
@RequestMapping("/api/auth")
public class JwtAuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;


    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (CustomException e) {
            throw new CustomException("Bad credentials", HttpStatus.UNAUTHORIZED);
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequest.getEmail());

        return ResponseEntity.ok(JwtResponse.builder().jwt(userDetails.getJwt())
                .user(userDetails.getUser()).build());
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        return new ResponseEntity<>(userService.createUser(signUpRequest), HttpStatus.CREATED);
    }
}
