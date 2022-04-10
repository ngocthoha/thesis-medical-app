package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.StoreMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreMedicineRepository extends JpaRepository<StoreMedicine, Integer> {
}
