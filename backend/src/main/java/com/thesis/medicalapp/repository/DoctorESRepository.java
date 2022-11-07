package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.DoctorES;
import com.thesis.medicalapp.models.HospitalServiceES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DoctorESRepository extends ElasticsearchRepository<DoctorES, String> {
}
