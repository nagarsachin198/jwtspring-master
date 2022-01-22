package com.example.jwtspring.springjwt.Service;

import com.example.jwtspring.springjwt.Exception.CustomException;
import com.example.jwtspring.springjwt.Model.BlackListToken;
import com.example.jwtspring.springjwt.Model.User;
import com.example.jwtspring.springjwt.Model.UserDetailsImpl;
import com.example.jwtspring.springjwt.Repo.BlackListRepository;
import com.example.jwtspring.springjwt.Repo.UserRepository;
import com.example.jwtspring.springjwt.Utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final BlackListRepository blackListRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        String jwt;

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Error: Username Not Found.", HttpStatus.NOT_FOUND));;;

        if (user.getActive()) {
            jwt = jwtUtils.generateToken(user);
        } else {
            throw new CustomException("Please Approve User", HttpStatus.UNPROCESSABLE_ENTITY);
        }

            return new UserDetailsImpl(jwt,user);
    }

    public void logout(HttpServletRequest request){
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);

        BlackListToken blackListToken = new BlackListToken();
        blackListToken.setToken(jwtToken);

        blackListRepository.save(blackListToken);
    }

}
