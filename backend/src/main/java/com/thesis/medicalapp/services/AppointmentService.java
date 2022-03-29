package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Appointment;
import com.thesis.medicalapp.pojo.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO saveAppointment(Appointment appointment);
    List<AppointmentDTO> getAppointmentByProfileId(String profileId);
    int updateAppointment(Appointment appointment);
    int removeAppointment(String id);
}
