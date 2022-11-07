package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.DoctorES;
import com.thesis.medicalapp.repository.DoctorESRepository;
import com.thesis.medicalapp.services.DoctorESService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DoctorESServiceImpl implements DoctorESService {
    private final DoctorESRepository doctorESRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public DoctorES save(Doctor doctor) {
        DoctorES doctorES = modelMapper.map(doctor, DoctorES.class);
        return doctorESRepository.save(doctorES);
    }
}
