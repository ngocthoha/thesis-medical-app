package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.StoreMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HospitalRepository extends JpaRepository<Hospital, String>, JpaSpecificationExecutor<Hospital> {
}
