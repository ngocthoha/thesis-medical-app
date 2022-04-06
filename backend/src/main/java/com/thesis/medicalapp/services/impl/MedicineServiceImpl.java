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
    public MedicineDTO saveMedicine(MedicineDTO medicineDTO){
        Medicine medicine = new Medicine();
        medicine.setName(medicineDTO.getName());
        medicine.setType(medicine.getType());
        medicine.setNumber(medicine.getNumber()); 
        medicine.setUse(medicine.getUse());
        Medicine medicineEntity = medicineRepository.save(medicine);
        MedicineDTO medicineResponse = MedicineDTO.from(medicineEntity);
        return medicineResponse;
    }
}
