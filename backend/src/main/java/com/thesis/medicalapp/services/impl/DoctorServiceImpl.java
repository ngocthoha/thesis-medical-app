package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.pojo.DoctorDTO;
import com.thesis.medicalapp.pojo.UserDoctorDTO;
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
    public List<UserDoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll()
                .stream().collect(Collectors.toList());
        List<UserDoctorDTO> doctorDTOS = doctors.stream().map(d -> {
            UserDoctorDTO doctorDTO = UserDoctorDTO.from(d);
            return doctorDTO;
        }).collect(Collectors.toList());

        return doctorDTOS;
    }
    @Override
    public Doctor findDoctorById(String id) {
        return doctorRepository.findDoctorById(id);
    }
    @Override
    public List<UserDoctorDTO> getDoctorsBySpecialty(String specialty) {
        List<Doctor> doctors = doctorRepository.findAllBySpecialty(specialty)
                .stream().collect(Collectors.toList());
        List<UserDoctorDTO> doctorDTOS = doctors.stream().map(d -> {
            UserDoctorDTO doctorDTO = UserDoctorDTO.from(d);
            return doctorDTO;
        }).collect(Collectors.toList());

        return doctorDTOS;
    }
}
