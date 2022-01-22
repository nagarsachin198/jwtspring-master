package com.example.jwtspring.springjwt.Repo;

import com.example.jwtspring.springjwt.Model.BlackListToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackListToken, String> {

    Boolean findByToken(String token);
}
