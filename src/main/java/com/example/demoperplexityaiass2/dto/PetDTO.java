package com.example.demoperplexityaiass2.dto;

public record PetDTO(
        Long id,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Type is required")
        String type,
        @NotBlank(message = "Breed is required")
        String breed,
        @Min(value = 0, message = "Age must be non-negative")
        int age,
        Long householdId
) {}