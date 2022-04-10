package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Medicine;
import com.thesis.medicalapp.pojo.MedicineDTO;
import com.thesis.medicalapp.repository.MedicineRepository;
import com.thesis.medicalapp.services.MedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public Medicine saveMedicine(Medicine medicine){
        Medicine medicineEntity = medicineRepository.save(medicine);
        return medicineEntity;
    }
}
