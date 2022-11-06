package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.pojo.DoctorDTO;
import com.thesis.medicalapp.pojo.UserDoctorDTO;
import com.thesis.medicalapp.search.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService {
    List<UserDoctorDTO> getDoctors();
    Doctor findDoctorById(String id);
    List<UserDoctorDTO> getDoctorsBySpecialty(String specialty);
    List<UserDoctorDTO> getDoctorsByHospital(String hospitalId);
    Page<Doctor> search(SearchRequest request);
}
