package com.example.jwtspring.springjwt.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@ToString
@Entity
public class BlackListToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

}
