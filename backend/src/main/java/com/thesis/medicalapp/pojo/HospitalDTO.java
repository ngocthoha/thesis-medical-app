package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.File;
import com.thesis.medicalapp.models.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {
    private String id;
    private String name;
    private String address;
    private String info;
    private Integer registrationNumber;
    private String mapImageUrl;
    private String hospitalImageUrl;

    public static HospitalDTO from(Hospital hospital) {
        return HospitalDTO.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .info(hospital.getInfo())
                .registrationNumber(hospital.getRegistrationNumber())
                .mapImageUrl(hospital.getMapImageUrl())
                .hospitalImageUrl(hospital.getHospitalImageUrl())
                .build();
    }
}
