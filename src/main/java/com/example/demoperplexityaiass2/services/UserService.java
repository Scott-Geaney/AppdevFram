package com.example.demoperplexityaiass2.services;

import com.example.petmanager.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUserPassword(Long id, String newPassword);
    UserDTO toggleUserLock(Long id);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();
}