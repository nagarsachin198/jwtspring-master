package com.example.jwtspring.springjwt.Controller;



import com.example.jwtspring.springjwt.Response.UserResponse;
import com.example.jwtspring.springjwt.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test")
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserResponse>> allAccess() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.FOUND);
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveUser(@PathVariable("id") Long id) {
        return  new ResponseEntity<>( userService.approveUser(id),HttpStatus.OK);
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> disableUser(@PathVariable("id") Long id) {
        return  new ResponseEntity<>( userService.disableUser(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return HttpStatus.OK;
    }


}

