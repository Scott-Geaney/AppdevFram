package com.example.demoperplexityaiass2.Controller;

import com.example.demoperplexityaiass2.dto.HouseholdDTO;
import com.example.demoperplexityaiass2.dto.PetDTO;
import com.example.demoperplexityaiass2.dto.PetStatisticsDTO;
import com.example.demoperplexityaiass2.services.HouseholdService;
import com.example.demoperplexityaiass2.services.PetService;
import com.example.demoperplexityaiass2.dto.*;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    private final HouseholdService householdService;
    private final PetService petService;

    public GraphQLController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    @QueryMapping
    public List<HouseholdDTO> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public List<PetDTO> getPetsByAnimalType(@Argument String type) {
        return petService.findPetsByAnimalType(type);
    }

    @QueryMapping
    public HouseholdDTO getHousehold(@Argument Long id) {
        return householdService.getHouseholdById(id);
    }

    @QueryMapping
    public PetDTO getPet(@Argument Long id) {
        return petService.getPetById(id);
    }

    @QueryMapping
    public PetStatisticsDTO getPetStatistics() {
        return petService.getPetStatistics();
    }

    @MutationMapping
    public HouseholdDTO createHousehold(@Argument HouseholdDTO input) {
        return householdService.createHousehold(input);
    }

    @MutationMapping
    public boolean deleteHousehold(@Argument Long id) {
        householdService.deleteHousehold(id);
        return true;
    }

    @MutationMapping
    public boolean deletePet(@Argument Long id) {
        petService.deletePet(id);
        return true;
    }
}