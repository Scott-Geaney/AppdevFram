package com.example.demoperplexityaiass2.repository;

import com.example.demoperplexityaiass2.entities.User;
import com.example.petmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}