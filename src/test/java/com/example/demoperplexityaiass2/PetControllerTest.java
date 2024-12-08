package com.example.demoperplexityaiass2;

import com.example.demoperplexityaiass2.Controller.PetController;
import com.example.demoperplexityaiass2.dto.PetDTO;
import com.example.demoperplexityaiass2.services.PetService;
import com.example.demoperplexityaiass2.dto.PetDTO;
import com.example.demoperplexityaiass2.services.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    public void testGetAllPets() throws Exception {
        mockMvc.perform(get("/api/pets"))
                .andExpect(status().isOk());
    }

    @Test
    @com.example.demoperplexityaiass2.WithMockUser(roles = "ADMIN")
    public void testCreatePet() throws Exception {
        PetDTO petDTO = new PetDTO(null, "Max", "Dog", "Labrador", 3, 1L);
        when(petService.createPet(any(PetDTO.class))).thenReturn(petDTO);

        mockMvc.perform(post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Max\",\"type\":\"Dog\",\"breed\":\"Labrador\",\"age\":3,\"householdId\":1}"))
                .andExpect(status().isCreated());
    }
}