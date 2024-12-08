package com.example.demoperplexityaiass2.Controller;

import com.example.demoperplexityaiass2.dto.HouseholdDTO;
import com.example.demoperplexityaiass2.dto.HouseholdStatisticsDTO;
import com.example.demoperplexityaiass2.services.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HouseholdDTO> createHousehold(@Valid @RequestBody HouseholdDTO householdDTO) {
        return new ResponseEntity<>(householdService.createHousehold(householdDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HouseholdDTO>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseholdDTO> getHouseholdById(@PathVariable Long id) {
        return ResponseEntity.ok(householdService.getHouseholdById(id));
    }

    @GetMapping("/eircode/{eircode}")
    public ResponseEntity<HouseholdDTO> getHouseholdByEircode(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.findHouseholdByEircode(eircode));
    }

    @GetMapping("/eircode/{eircode}/with-pets")
    public ResponseEntity<HouseholdDTO> getHouseholdByEircodeWithPets(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.findHouseholdByEircodeWithPets(eircode));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HouseholdDTO> updateHousehold(@PathVariable Long id, @Valid @RequestBody HouseholdDTO householdDTO) {
        return ResponseEntity.ok(householdService.updateHousehold(id, householdDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHousehold(@PathVariable Long id) {
        householdService.deleteHousehold(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<HouseholdDTO>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/owner-occupied")
    public ResponseEntity<List<HouseholdDTO>> getOwnerOccupiedHouseholds() {
        return ResponseEntity.ok(householdService.getOwnerOccupiedHouseholds());
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HouseholdStatisticsDTO> getHouseholdStatistics() {
        return ResponseEntity.ok(householdService.getHouseholdStatistics());
    }
}