package com.example.jwtspring.springjwt.Repo;

import com.example.jwtspring.springjwt.Model.BlackListToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackListToken, Long> {

    Optional<BlackListToken> findByToken(String token);
}
