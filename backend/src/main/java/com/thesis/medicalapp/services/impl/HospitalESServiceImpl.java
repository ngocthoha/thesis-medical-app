package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.HospitalES;
import com.thesis.medicalapp.repository.DoctorESRepository;
import com.thesis.medicalapp.repository.HospitalESRepository;
import com.thesis.medicalapp.services.HospitalESService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HospitalESServiceImpl implements HospitalESService {
    private final HospitalESRepository hospitalESRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public HospitalES save(Hospital hospital) {
        HospitalES hospitalES = modelMapper.map(hospital, HospitalES.class);
        return hospitalESRepository.save(hospitalES);
    }
}
