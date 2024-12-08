package com.example.demoperplexityaiass2;

import com.example.demoperplexityaiass2.dto.HouseholdDTO;
import com.example.demoperplexityaiass2.entities.Household;
import com.example.demoperplexityaiass2.repository.HouseholdRepository;
import com.example.demoperplexityaiass2.services.HouseholdServiceImp;
import com.example.demoperplexityaiass2.dto.HouseholdDTO;
import com.example.demoperplexityaiass2.entities.Household;
import com.example.demoperplexityaiass2.repository.HouseholdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HouseholdServiceImplTest {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImp householdService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindHouseholdByEircode() {
        Household household = new Household();
        household.setId(1L);
        household.setEircode("A65F4E2");
        household.setNumberOfOccupants(2);
        household.setMaxNumberOfOccupants(4);
        household.setOwnerOccupied(true);

        when(householdRepository.findByEircode("A65F4E2")).thenReturn(Optional.of(household));

        HouseholdDTO result = householdService.findHouseholdByEircode("A65F4E2");

        assertEquals("A65F4E2", result.eircode());
        assertEquals(2, result.numberOfOccupants());
        assertEquals(4, result.maxNumberOfOccupants());
        assertEquals(true, result.ownerOccupied());
    }
}
