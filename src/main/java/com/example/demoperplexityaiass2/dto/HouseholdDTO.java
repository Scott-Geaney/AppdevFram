package com.example.demoperplexityaiass2.dto;

public record HouseholdDTO(
        Long id,
        @NotBlank(message = "Eircode is required")
        String eircode,
        @Min(value = 1, message = "Number of occupants must be at least 1")
        int numberOfOccupants,
        @Min(value = 1, message = "Max number of occupants must be at least 1")
        int maxNumberOfOccupants,
        boolean ownerOccupied
) {}