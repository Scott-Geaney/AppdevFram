package com.example.demoperplexityaiass2.Controller;

import com.example.demoperplexityaiass2.dto.UserDTO;
import com.example.demoperplexityaiass2.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{id}/password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUserPassword(@PathVariable Long id, @RequestBody String newPassword) {
        return ResponseEntity.ok(userService.updateUserPassword(id, newPassword));
    }

    @PutMapping("/{id}/toggle-lock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> toggleUserLock(@PathVariable Long id) {
        return ResponseEntity.ok(userService.toggleUserLock(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}