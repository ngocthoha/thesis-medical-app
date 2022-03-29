package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findAllByProfileId(String profileId);
    Appointment findAppointmentById(String id);
}
