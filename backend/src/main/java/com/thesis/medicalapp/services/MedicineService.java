package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Medicine;
import com.thesis.medicalapp.pojo.MedicineDTO;

public interface MedicineService {
    MedicineDTO saveMedicine(MedicineDTO medicine);
}
