package com.example.demoperplexityaiass2.dto;

public record UserDTO(
        Long id,
        String username,
        String password,
        String role,
        boolean unlocked
) {}