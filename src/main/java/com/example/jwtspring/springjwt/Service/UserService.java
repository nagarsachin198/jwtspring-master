package com.example.jwtspring.springjwt.Service;



import com.example.jwtspring.springjwt.Exception.CustomException;
import com.example.jwtspring.springjwt.Model.Role;
import com.example.jwtspring.springjwt.Model.User;
import com.example.jwtspring.springjwt.Repo.UserRepository;
import com.example.jwtspring.springjwt.Request.SignupRequest;
import com.example.jwtspring.springjwt.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public User createUser(SignupRequest signupRequest){
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw  new CustomException("Email Already exits", HttpStatus.ALREADY_REPORTED);
        }

        User user = new User(signupRequest.getName(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()), signupRequest.getMobile());

        Role strRoles = signupRequest.getRole();

        if (strRoles == Role.ROLE_USER) {
            user.setRole(Role.ROLE_USER);
            user.setActive(false);
        } else if (strRoles == Role.ROLE_ADMIN) {
            user.setRole(Role.ROLE_ADMIN);
            user.setActive(true);
        }

        user.setRole(strRoles);
        userRepository.save(user);

        return user;
    }
    private UserResponse prepareResponse(User user) {
        return UserResponse.builder().username(user.getName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .role(user.getRole()).build();
    }

    public List<UserResponse> findAll() {
        List<UserResponse> userResponseList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            userResponseList.add(prepareResponse(user));
        }
        return userResponseList;
    }

    public User approveUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("Error: User Not Found.", HttpStatus.NOT_FOUND));
        user.setActive(true);
        userRepository.save(user);
        return user;
    }

    public User disableUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new CustomException("User Not Found",HttpStatus.NOT_FOUND));

        if(user.getRole().equals(Role.ROLE_ADMIN)){
            throw new CustomException("Admin Can't Change",HttpStatus.BAD_REQUEST);
        }
        else {
            user.setActive(false);
        }
        userRepository.save(user);
        return user;

    }

    public boolean deleteUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        else {
            throw new CustomException("User Not Found",HttpStatus.NOT_FOUND);
        }
    }


}

