package com.example.demoperplexityaiass2.Controller;

import com.example.demoperplexityaiass2.dto.PetDTO;
import com.example.demoperplexityaiass2.dto.PetStatisticsDTO;
import com.example.demoperplexityaiass2.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PetDTO> createPet(@Valid @RequestBody PetDTO petDTO) {
        return new ResponseEntity<>(petService.createPet(petDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long id, @Valid @RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.updatePet(id, petDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePetsByName(@PathVariable String name) {
        petService.deletePetsByName(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PetDTO>> findPetsByAnimalType(@PathVariable String type) {
        return ResponseEntity.ok(petService.findPetsByAnimalType(type));
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<PetDTO>> findPetsByBreed(@PathVariable String breed) {
        return ResponseEntity.ok(petService.findPetsByBreed(breed));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<PetDTO>> getPetSummaries() {
        return ResponseEntity.ok(petService.getPetSummaries());
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PetStatisticsDTO> getPetStatistics() {
        return ResponseEntity.ok(petService.getPetStatistics());
    }
}