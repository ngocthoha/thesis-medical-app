package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.StoreMedicine;

import java.util.List;

public interface StoreMedicineService {
    List<StoreMedicine> getStoreMedicines();
    StoreMedicine saveStoreMedicine(StoreMedicine storeMedicine);
}
