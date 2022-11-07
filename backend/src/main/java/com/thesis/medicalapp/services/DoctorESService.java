package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.DoctorES;

public interface DoctorESService {
    DoctorES save(Doctor doctor);
}
