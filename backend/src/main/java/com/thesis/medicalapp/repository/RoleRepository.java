package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.model.Role;
import com.thesis.medicalapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
