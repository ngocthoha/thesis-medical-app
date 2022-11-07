package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.HospitalES;

public interface HospitalESService {
    HospitalES save(Hospital hospital);
}
