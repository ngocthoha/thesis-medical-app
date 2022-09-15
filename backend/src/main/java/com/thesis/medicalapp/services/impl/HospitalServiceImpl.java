package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.repository.HospitalRepository;
import com.thesis.medicalapp.services.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    @Override
    public HospitalDTO saveHospital(HospitalDTO hospitalDTO) {
        log.info("Saving new profile of {} to the database", hospitalDTO.getName());
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDTO.getName());
        hospital.setAddress(hospitalDTO.getAddress());
        hospital.setInfo(hospitalDTO.getInfo());
        hospital.setRegistrationNumber(0);
        hospital.setMapImageUrl(hospitalDTO.getMapImageUrl());
        hospital.setHospitalImageUrl(hospitalDTO.getHospitalImageUrl());
        Hospital hospital1 = hospitalRepository.save(hospital);
        HospitalDTO hospitalDTO1 = HospitalDTO.from(hospital1);
        return hospitalDTO1;
    }
    @Override
    public List<HospitalDTO> getHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll()
                .stream().collect(Collectors.toList());
        List<HospitalDTO> hospitalDTOS = hospitals.stream().map(d -> {
            HospitalDTO hospitalDTO = HospitalDTO.from(d);
            return hospitalDTO;
        }).collect(Collectors.toList());
        return hospitalDTOS;
    }
    @Override
    public HospitalDTO getHospitalById(String id) {
        Optional<Hospital> hospitalOp = hospitalRepository.findById(id);
        Hospital hospital = hospitalOp.get();
        return HospitalDTO.from(hospital);
    }
}