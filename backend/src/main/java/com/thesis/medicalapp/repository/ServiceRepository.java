package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.HospitalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<HospitalService, String> {
    List<HospitalService> findByHospitalId(String hospitalId);
}
