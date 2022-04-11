package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.repository.DoctorRepository;
import com.thesis.medicalapp.repository.RoomRepository;
import com.thesis.medicalapp.repository.ScheduleRepository;
import com.thesis.medicalapp.repository.UserRepository;
import com.thesis.medicalapp.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;

    @Override
    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setTimes(scheduleDTO.getTimes());
        Room room = roomRepository.save(scheduleDTO.getRoom());
        schedule.setRoom(room);
        Doctor doctor = doctorRepository.findDoctorById(scheduleDTO.getDoctor().getId());
        schedule.setDoctor(doctor);
        log.info("Saving new schedule of {} to the database", doctor.getName());
        scheduleRepository.save(schedule);
        ScheduleDTO scheduleResponse = ScheduleDTO.from(schedule);
        return scheduleResponse;
    }
    @Override
    public List<ScheduleDTO> getSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        List<ScheduleDTO> scheduleDTOS = schedules.stream().map(s -> {
            ScheduleDTO scheduleDTO = ScheduleDTO.from(s);
            return scheduleDTO;
        }).collect(Collectors.toList());
        return scheduleDTOS;
    }
    @Override
    public List<ScheduleDTO> getDoctorsBySchedule(String specialty, String date) {
        Date dateFormat = new Date();
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<Schedule> schedules = scheduleRepository.findAllByDate(dateFormat);
        List<ScheduleDTO> schedulesDTO = new ArrayList<>();
        for (Schedule schedule: schedules) {
            if(schedule.getDoctor().getSpecialty().equals(specialty)) {
                ScheduleDTO scheduleDTO = ScheduleDTO.from(schedule);
                schedulesDTO.add(scheduleDTO);
            }
        }
        return schedulesDTO;
    }
    @Override
    public int updateSchedule(ScheduleDTO scheduleDTO) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleDTO.getId());
        Doctor doctor = doctorRepository.findDoctorById(scheduleDTO.getDoctor().getId());
        return schedule.map(s -> {
            s.setDate(scheduleDTO.getDate());
            s.setRoom(scheduleDTO.getRoom());
            s.setTimes(scheduleDTO.getTimes());
            s.setDoctor(doctor);
            return 1;
        }).orElseGet(() -> 0);
    }
    @Override
    public int removeSchedule(Integer id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.map(s -> {
            scheduleRepository.delete(s);
            return 1;
        }).orElseGet(() -> 0);
    }
    public List<ScheduleDTO> getSchedulesByDoctor() {
        Doctor doctor = doctorRepository.findDoctorByUsername(Global.user.getUsername());
        List<Schedule> schedules = scheduleRepository.findAllByDoctor(doctor)
                .stream()
                .collect(Collectors.toList());
        List<ScheduleDTO> scheduleDTOS = schedules.stream().map(s -> {
            ScheduleDTO scheduleDTO = ScheduleDTO.from(s);
            return scheduleDTO;
        }).collect(Collectors.toList());
        return scheduleDTOS;
    }
    @Override
    public ScheduleDTO getScheduleByDateAndDoctor(Date date, Doctor doctor) {
        Schedule schedule = scheduleRepository.findByDateAndDoctor(date, doctor);
        ScheduleDTO scheduleDTO = ScheduleDTO.from(schedule);
        return scheduleDTO;
    }
}
