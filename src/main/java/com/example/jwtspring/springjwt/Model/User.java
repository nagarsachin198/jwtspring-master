package com.example.jwtspring.springjwt.Model;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    @Size(min = 8 )
    private String password;
    private Boolean active;
    @NotNull
    @Column(unique = true)
    private String mobile;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, String email, String password,String mobile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
    }
}
