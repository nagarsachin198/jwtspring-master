//package com.example.jwtspring.springjwt.Service;
//
//import com.example.jwtspring.springjwt.Model.Role;
//import com.example.jwtspring.springjwt.Model.User;
//import com.example.jwtspring.springjwt.Repo.UserRepository;
//import com.example.jwtspring.springjwt.Request.SignupRequest;
//import com.example.jwtspring.springjwt.Response.UserResponse;
//import org.junit.jupiter.api.*;
//import org.junit.runner.RunWith;
//import org.junit.runner.Runner;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static junit.framework.TestCase.assertEquals;
//import static org.junit.Assert.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//@RunWith(Runner.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = {UserServiceTest.class})
//class UserServiceTest {
//
//
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private UserService userService;
//    private List<User> user;
//
//    @BeforeAll
//    void setup() {
//        user = new ArrayList<>();
//        user.add(new User(1L, "sachin", "sachin123@gmail.com", "sachin123", false, "8695656647682", Role.ROLE_USER));
//        user.add(new User(1L, "test", "test@gmail.com", "test123", true, "8696747682", Role.ROLE_ADMIN));
//
//    }
//
//    @Test
//    void test_createUser() {
//        SignupRequest signupRequest = new SignupRequest("test123", "test123@gmail.com", "test123", Role.ROLE_USER, "8696647686");
//        User user = new User(signupRequest.getName(),
//                signupRequest.getEmail(),
//                signupRequest.getPassword(), signupRequest.getMobile());
//        given(userRepository.save(user)).willReturn(user);
//        User user1 = userService.createUser(signupRequest);
//        assertEquals(user1.getEmail(), "test123@gmail.com");
//        assertEquals(user1.getRole(), Role.ROLE_ADMIN);
//        assertTrue(user1.getActive());
//
//    }
//
//    @Test
//    void test_findAll() {
//
//        when(userRepository.findAll()).thenReturn(user);
//        List<UserResponse> userResponseList = userService.findAll();
//        assertEquals(userResponseList.size(), 2);
//        assertEquals(userResponseList.listIterator().next().getEmail(), "sachin123@gmail.com");
//        assertEquals(userResponseList.get(1).getMobile(), "8696747682");
//    }
//
//    @Test
//    void test_approveUser() {
//        User user = new User(1L, "sachin", "sachin123@gmail.com", "sachin123", false, "8695656647682", Role.ROLE_USER);
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        User user1 = userService.approveUser(1L);
//        assertTrue(user1.getActive());
//
//    }
//
//    @Test
//    void test_disableUser() {
//        User user = new User(1L, "sachin", "sachin123@gmail.com", "sachin123", true, "8695656647682", Role.ROLE_USER);
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        User user1 = userService.disableUser(1L);
//        assertFalse(user1.getActive());
//    }
//
//    @Test
//    void deleteUserById() {
//        User user = new User(1L, "sachin", "sachin123@gmail.com", "sachin123", false, "8695656647682", Role.ROLE_USER);
//        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
//        Boolean aBoolean = userService.deleteUserById(2L);
//        assertTrue(aBoolean);
//    }
//}