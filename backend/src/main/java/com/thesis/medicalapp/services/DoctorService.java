package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.pojo.DoctorDTO;
import com.thesis.medicalapp.pojo.UserDoctorDTO;

import java.util.List;

public interface DoctorService {
    List<UserDoctorDTO> getDoctors();
    Doctor findDoctorById(String id);
    List<UserDoctorDTO> getDoctorsBySpecialty(String specialty);
}
