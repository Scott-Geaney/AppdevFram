package com.example.demoperplexityaiass2;

import com.example.demoperplexityaiass2.dto.PetDTO;
import com.example.demoperplexityaiass2.entities.Pet;
import com.example.demoperplexityaiass2.repository.PetRepository;
import com.example.demoperplexityaiass2.services.PetServiceImp;
import com.example.demoperplexityaiass2.dto.PetDTO;
import com.example.demoperplexityaiass2.entities.Pet;
import com.example.demoperplexityaiass2.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImp petService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPetById() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Max");
        pet.setAnimalType("Dog");
        pet.setBreed("Labrador");
        pet.setAge(3);

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        PetDTO result = petService.getPetById(1L);

        assertEquals("Max", result.name());
        assertEquals("Dog", result.type());
        assertEquals("Labrador", result.breed());
        assertEquals(3, result.age());
    }
}