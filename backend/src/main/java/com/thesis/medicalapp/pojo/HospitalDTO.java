package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Address;
import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.HospitalHour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {
    private String id;
    @NotBlank
    @NotEmpty
    private String name;
    private Address address;
    private String info;
    private Integer registrationNumber;
    private String mapImageUrl;
    private String hospitalImageUrl;
    private Boolean isActive;
    private HospitalHour hospitalHour;

    public static HospitalDTO from(Hospital hospital) {
        return HospitalDTO.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .info(hospital.getInfo())
                .registrationNumber(hospital.getRegistrationNumber())
                .mapImageUrl(hospital.getMapImageUrl())
                .hospitalImageUrl(hospital.getHospitalImageUrl())
                .isActive(hospital.getIsActive())
                .hospitalHour(hospital.getHospitalHour())
                .build();
    }
}
