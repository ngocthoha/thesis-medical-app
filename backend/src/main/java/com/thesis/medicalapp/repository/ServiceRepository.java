package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.HospitalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<HospitalService, String> {
}
