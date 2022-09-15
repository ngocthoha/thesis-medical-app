package com.thesis.medicalapp.services;

import com.thesis.medicalapp.pojo.HospitalDTO;

import java.util.List;

public interface HospitalService {
    HospitalDTO saveHospital(HospitalDTO hospitalDTO);
    List<HospitalDTO> getHospitals();
    HospitalDTO getHospitalById(String id);
}
