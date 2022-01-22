package com.example.jwtspring.springjwt.Model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class BlackListToken {

    @Id
    private String token;

}
