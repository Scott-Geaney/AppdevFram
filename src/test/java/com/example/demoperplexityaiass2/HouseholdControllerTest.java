package com.example.demoperplexityaiass2;

import com.example.demoperplexityaiass2.Controller.HouseholdController;
import com.example.demoperplexityaiass2.dto.HouseholdDTO;
import com.example.demoperplexityaiass2.services.HouseholdService;
import com.example.demoperplexityaiass2.dto.HouseholdDTO;
import com.example.demoperplexityaiass2.services.HouseholdService;
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

@WebMvcTest(HouseholdController.class)
public class HouseholdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseholdService householdService;

    @Test
    public void testGetAllHouseholds() throws Exception {
        mockMvc.perform(get("/api/households"))
                .andExpect(status().isOk());
    }

    @Test
    @com.example.demoperplexityaiass2.WithMockUser(roles = "ADMIN")
    public void testCreateHousehold() throws Exception {
        HouseholdDTO householdDTO = new HouseholdDTO(null, "A65F4E2", 2, 4, true);
        when(householdService.createHousehold(any(HouseholdDTO.class))).thenReturn(householdDTO);

        mockMvc.perform(post("/api/households")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"eircode\":\"A65F4E2\",\"numberOfOccupants\":2,\"maxNumberOfOccupants\":4,\"ownerOccupied\":true}"))
                .andExpect(status().isCreated());
    }
}