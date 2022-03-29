package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private String id;
    private String name;
    private String specialty;
    private String level;

    public static DoctorDTO from(Doctor doctor){
        return DoctorDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .specialty(doctor.getSpecialty())
                .level(doctor.getLevel())
                .build();
    }
}
