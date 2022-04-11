package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.StoreMedicine;
import com.thesis.medicalapp.repository.StoreMedicineRepository;
import com.thesis.medicalapp.services.StoreMedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreMedicineServiceImpl implements StoreMedicineService {
    private final StoreMedicineRepository storeMedicineRepository;

    @Override
    public List<StoreMedicine> getStoreMedicines() {
        return storeMedicineRepository.findAll();
    }
    @Override
    public StoreMedicine saveStoreMedicine(StoreMedicine storeMedicine) {
        return storeMedicineRepository.save(storeMedicine);
    }
}
