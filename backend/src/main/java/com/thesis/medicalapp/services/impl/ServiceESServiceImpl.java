package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.models.HospitalServiceES;
import com.thesis.medicalapp.repository.ServiceESRepository;
import com.thesis.medicalapp.services.ServiceESService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServiceESServiceImpl implements ServiceESService {
    private final ServiceESRepository serviceESRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public HospitalServiceES save(HospitalService hospitalService) {
        HospitalServiceES hospitalServiceES = modelMapper.map(hospitalService, HospitalServiceES.class);
        return serviceESRepository.save(hospitalServiceES);
    }
}
