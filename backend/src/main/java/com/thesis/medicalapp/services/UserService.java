package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Role;
import com.thesis.medicalapp.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
