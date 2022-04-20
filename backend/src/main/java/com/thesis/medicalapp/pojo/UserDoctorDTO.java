package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDoctorDTO {
    private String id;
    private String username;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private Date dob;
    private String specialty;
    private String level;

    public static UserDoctorDTO from(Doctor doctor) {
        return UserDoctorDTO.builder()
                .id(doctor.getId())
                .username(doctor.getUsername())
                .name(doctor.getName())
                .email(doctor.getEmail())
                .address(doctor.getAddress())
                .phoneNumber(doctor.getPhoneNumber())
                .dob(doctor.getDob())
                .specialty(doctor.getSpecialty())
                .level(doctor.getLevel())
                .build();
    }
}
