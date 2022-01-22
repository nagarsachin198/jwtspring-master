package com.example.jwtspring.springjwt.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public CustomException() {
        super();
    }
//    public CustomException(String message, Throwable cause) {
//        super(message, cause);
//    }
    public CustomException(String message,HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
//    public CustomException(Throwable cause) {
//        super(cause);
//    }
}
