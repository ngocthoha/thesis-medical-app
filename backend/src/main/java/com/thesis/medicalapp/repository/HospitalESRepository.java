package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.HospitalES;
import com.thesis.medicalapp.models.HospitalServiceES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HospitalESRepository extends ElasticsearchRepository<HospitalES, String> {
}
