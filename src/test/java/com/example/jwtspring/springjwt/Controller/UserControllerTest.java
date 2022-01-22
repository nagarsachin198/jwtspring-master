//package com.example.jwtspring.springjwt.Controller;
//
//import com.example.jwtspring.springjwt.Model.User;
//import lombok.RequiredArgsConstructor;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.runner.RunWith;
//import org.junit.runner.Runner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//
//@RunWith(Runner.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest
//@RequiredArgsConstructor
//class UserControllerTest {
//
//    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
//    private static final String BASE_URL = "http://localhost:8080/api/test";
//
//
//    @Test
//    public void test_allAccess() throws URISyntaxException {
//        URI uri = new URI(BASE_URL.concat("/all"));
//        ResponseEntity<String> user = testRestTemplate.getForEntity(uri, String.class);
//        Assert.assertEquals(200, user.getStatusCodeValue());
//        Assert.assertEquals(true, user.getBody().contains("employeeList"));
//    }
//
//    @Test
//    public void test_approveUser() throws URISyntaxException {
//        URI uri = new URI(BASE_URL.concat("/approve/6"));
//        User user = new User();
//        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(user), String.class);
//        Assert.assertEquals(200, response.getStatusCodeValue());
//        Assert.assertEquals(true, response.getBody().contains("Deepak"));
//    }
//
//    @Test
//    public void test_disableUser() throws URISyntaxException {
//        URI uri = new URI(BASE_URL.concat("/disable/4"));
//        User user = new User();
//        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(user), String.class);
//        Assert.assertEquals(200, response.getStatusCodeValue());
//        Assert.assertEquals(true, response.getBody().contains("Deepak"));
//
//    }
//
////    @Test
////    public void test_deleteUser() throws URISyntaxException {
////        URI uri = new URI(BASE_URL.concat("/delete/5"));
////        testRestTemplate.delete(uri);
////    }
//
//}