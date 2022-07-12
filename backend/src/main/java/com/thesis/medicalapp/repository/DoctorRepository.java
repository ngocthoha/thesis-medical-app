package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findDoctorById(String id);
    Doctor findDoctorByUsername(String username);
    List<Doctor> findAllBySpecialty(String specialty);
}
