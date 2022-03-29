package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Appointment;
import com.thesis.medicalapp.pojo.AppointmentDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.services.AppointmentService;
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
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDTO saveAppointment(Appointment appointment) {
        Appointment appointmentEntity = appointmentRepository.save(appointment);
        AppointmentDTO appointmentResponse = AppointmentDTO.from(appointmentEntity);
        return appointmentResponse;
    }
    @Override
    public List<AppointmentDTO> getAppointmentByProfileId(String profileId) {
        List<Appointment> appointments = appointmentRepository.findAllByProfileId(profileId)
                .stream()
                .collect(Collectors.toList());
        List<AppointmentDTO> appointmentDTOS = appointments.stream().map(a -> {
            AppointmentDTO appointmentDTO = AppointmentDTO.from(a);
            return appointmentDTO;
        }).collect(Collectors.toList());
        return appointmentDTOS;
    }
    @Override
    public int updateAppointment(Appointment appointment) {
        try {
            appointmentRepository.save(appointment);
            return 1;
        } catch (Exception e) {
            System.out.println("Error in appointmet service " + e.getMessage());
            return 0;
        }
    }
    @Override
    public int removeAppointment(String id) {
        try {
            appointmentRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            System.out.println("Error in appointment service " + e.getMessage());
            return 0;
        }
    }
}
