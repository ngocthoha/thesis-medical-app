package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.Gender;
import com.thesis.medicalapp.models.Hospital;
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
    private String name;
    private Gender gender;
    private String email;
    private String phone;
    private Date dob;
    private String specialty;
    private String level;
    private String bio;
    private Integer registrationNumber;
    private String price;
    private String imageUrl;
    private HospitalDTO hospital;

    public static UserDoctorDTO from(Doctor doctor) {
        return UserDoctorDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .gender(doctor.getGender())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .dob(doctor.getDob())
                .specialty(doctor.getSpecialty())
                .level(doctor.getLevel())
                .bio(doctor.getBio())
                .registrationNumber(doctor.getRegistrationNumber())
                .price(doctor.getPrice())
                .hospital(HospitalDTO.from(doctor.getHospital()))
                .imageUrl(doctor.getImageUrl())
                .build();
    }
}
