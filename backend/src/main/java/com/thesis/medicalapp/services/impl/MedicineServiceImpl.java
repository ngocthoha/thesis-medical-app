package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Medicine;
import com.thesis.medicalapp.pojo.MedicineDTO;
import com.thesis.medicalapp.repository.MedicineRepository;
import com.thesis.medicalapp.services.MedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    @Override
    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }
}
