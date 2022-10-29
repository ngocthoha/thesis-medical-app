package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.models.ScheduleType;
import com.thesis.medicalapp.models.SpecialtyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private String id;
    private String name;
    private String info;
    private Integer registrationNumber;
    private String price;
    private String specialty;
    private HospitalDTO hospital;
    private String serviceImageUrl;
    private Integer numOfServicePerHour;
    private ScheduleType type;

    public static ServiceDTO from(HospitalService hospitalService) {
        return ServiceDTO.builder()
                .id(hospitalService.getId())
                .name(hospitalService.getName())
                .info(hospitalService.getInfo())
                .registrationNumber(hospitalService.getRegistrationNumber())
                .price(hospitalService.getPrice())
                .specialty(hospitalService.getSpecialty().getName())
                .hospital(HospitalDTO.from(hospitalService.getHospital()))
                .serviceImageUrl(hospitalService.getServiceImageUrl())
                .numOfServicePerHour(hospitalService.getNumOfServicePerHour())
                .type(hospitalService.getType())
                .build();
    }
}
