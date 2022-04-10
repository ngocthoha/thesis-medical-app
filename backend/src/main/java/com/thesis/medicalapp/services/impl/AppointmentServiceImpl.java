package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.pojo.AppointmentDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.repository.DoctorRepository;
import com.thesis.medicalapp.services.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

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
    @Override
    public List<Object> getAppointmentsByDateAndDoctor(String date) {
        Doctor doctor = doctorRepository.findDoctorByUsername(Global.user.getUsername());
        Date dateFormat = new Date();
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<Appointment> appointments = appointmentRepository.findAllByDateAndDoctor(dateFormat, doctor)
                .stream()
                .collect(Collectors.toList());
        List<AppointmentDTO> appointmentDTOS = appointments.stream().map(a -> {
            AppointmentDTO appointmentDTO = AppointmentDTO.from(a);
            return appointmentDTO;
        }).collect(Collectors.toList());

        List<Object> obs = new ArrayList<>();
        for (String t : Time.times) {
            List<AppointmentDTO> aps = appointmentDTOS.stream().filter(a -> a.getTime().equals(t)).collect(Collectors.toList());
            if(!aps.isEmpty()) {
                obs.add(new AppointmentsByDateAndDoctor(t, aps));
            }
        }
        return obs;
    }
    @Override
    public Integer max(Date date) {
        return appointmentRepository.max(date);
    }
    @Override
    public Appointment findAppointmentById(String id) {
        return appointmentRepository.findAppointmentById(id);
    }
}
@Data
@AllArgsConstructor
class AppointmentsByDateAndDoctor {
    private String time;
    private List<AppointmentDTO> appointments;
}
