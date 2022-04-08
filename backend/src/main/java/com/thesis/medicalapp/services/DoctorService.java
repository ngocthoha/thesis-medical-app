package com.thesis.medicalapp.services;

import com.thesis.medicalapp.pojo.DoctorDTO;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getDoctors();
}
