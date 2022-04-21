package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Appointment;
import com.thesis.medicalapp.pojo.AppointmentDTO;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO saveAppointment(Appointment appointment);
    List<AppointmentDTO> getAppointmentByProfileId(String profileId);
    List<Object> getAppointmentsByDateAndDoctor(String date);
    int updateAppointment(Appointment appointment);
    int removeAppointment(String id);
    Integer max(Date date);
    Appointment findAppointmentById(String id);
    List<?> getAllByDateIsBetweenAndDoctor(Date start, Date end);
}
