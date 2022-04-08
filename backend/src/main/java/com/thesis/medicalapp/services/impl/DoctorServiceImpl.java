package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.pojo.DoctorDTO;
import com.thesis.medicalapp.repository.DoctorRepository;
import com.thesis.medicalapp.services.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    @Override
    public List<DoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll()
                .stream().collect(Collectors.toList());
        List<DoctorDTO> doctorDTOS = doctors.stream().map(d -> {
            DoctorDTO doctorDTO = DoctorDTO.from(d);
            return doctorDTO;
        }).collect(Collectors.toList());
        return doctorDTOS;
    }
}
