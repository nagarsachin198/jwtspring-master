package com.example.jwtspring.springjwt.Utils;


import com.example.jwtspring.springjwt.Exception.CustomException;
import com.example.jwtspring.springjwt.Model.UserDetailsImpl;
import com.example.jwtspring.springjwt.Repo.BlackListRepository;
import com.example.jwtspring.springjwt.Service.UserDetailsServiceImpl;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    @Autowired
    public BlackListRepository blackListRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");
        String email = null;
        String jwtToken = null;


        //null and format
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken = requestTokenHeader.substring(7);

            try {
                email = jwtUtils.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
            }
        }

        if (blackListRepository.findByToken(jwtToken).isPresent()) {
            log.info("You are logout.....!!!!!");
            throw new CustomException("You are logout.....!!!!!", HttpStatus.UNAUTHORIZED);
        }



        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);


            if (jwtUtils.validateToken(jwtToken, userDetails)) {


                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

        }
        filterChain.doFilter(request, response);
    }
}
