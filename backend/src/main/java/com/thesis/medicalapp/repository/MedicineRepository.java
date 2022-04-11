package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
