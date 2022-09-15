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
    private String email;
    private String specialty;
    private String level;
    private String bio;
    private Integer registrationNumber;
    private String price;

    public static DoctorDTO from(Doctor doctor){
        return DoctorDTO.builder()
                .id(doctor.getId())
                .name(doctor.getUsername())
                .specialty(doctor.getSpecialty())
                .level(doctor.getLevel())
                .bio(doctor.getBio())
                .registrationNumber(doctor.getRegistrationNumber())
                .price(doctor.getPrice())
                .build();
    }
}
