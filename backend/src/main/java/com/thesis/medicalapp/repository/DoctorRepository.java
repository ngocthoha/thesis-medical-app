package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.StoreMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String>, JpaSpecificationExecutor<Doctor> {
    Doctor findDoctorById(String id);
    Doctor findDoctorByUsername(String username);
    List<Doctor> findAllBySpecialty(String specialty);
    List<Doctor> findAllByHospital(Hospital hospital);
}
