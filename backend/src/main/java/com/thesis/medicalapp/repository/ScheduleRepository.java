package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByDate(Date date);
    List<Schedule> findAllByDoctor(Doctor doctor);
}
