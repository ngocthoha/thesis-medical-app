package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {
    List<Record> findByAppointment_Profile_Id(String profileId);
}
