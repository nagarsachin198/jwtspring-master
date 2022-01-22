//package com.example.jwtspring.springjwt.Service;
//
//import com.example.jwtspring.springjwt.Model.Role;
//import com.example.jwtspring.springjwt.Model.User;
//import com.example.jwtspring.springjwt.Model.UserDetailsImpl;
//import com.example.jwtspring.springjwt.Repo.UserRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.runner.RunWith;
//import org.junit.runner.Runner;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(Runner.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = {UserDetailsServiceImplTest.class})
//class UserDetailsServiceImplTest {
//
//
//    @Mock
//    UserRepository userRepository;
//    @InjectMocks
//    UserDetailsServiceImpl userDetailsService;
//    User user;
//
//    @BeforeAll
//    public void setUp() {
//        user = new User(1L, "sachin", "sachin123@gmail.com", "sachin123", false, "8695656647682", Role.ROLE_ADMIN);
//    }
//
//    @Test
//    public void test_loadUserByUsername() {
//        when(userRepository.findByEmail("sachin123@gmail.com")).thenReturn(Optional.of(user));
//        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername("sachin123@gmail.com");
//
//        assertEquals(userDetails.getUser().getPassword(), "sachin123");
//        assertEquals(userDetails.getUser().getName(),"sachin");
//        assertEquals(userDetails.getUser().getRole(), Role.ROLE_ADMIN);
//        assertFalse(userDetails.getUser().getActive());
//
//    }
//
//}