package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, String> {
}
