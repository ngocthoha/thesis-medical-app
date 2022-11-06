package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.HospitalService;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<HospitalService, String> {
    List<HospitalService> findByHospitalId(String hospitalId);
}
