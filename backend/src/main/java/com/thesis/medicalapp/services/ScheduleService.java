package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.models.Schedule;
import com.thesis.medicalapp.pojo.ScheduleDTO;

import java.util.Date;
import java.util.List;

public interface ScheduleService {
    ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO);
    List<ScheduleDTO> getSchedules();
    List<ScheduleDTO> getDoctorsBySchedule(String specialty, String date);
    int updateSchedule(ScheduleDTO scheduleDTO);
    int removeSchedule(Integer id);
    List<ScheduleDTO> getSchedulesByDoctor();
}
