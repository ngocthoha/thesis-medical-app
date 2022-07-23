package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Service;
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

    public static ServiceDTO from(Service service) {
        return ServiceDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .info(service.getInfo())
                .registrationNumber(service.getRegistrationNumber())
                .price(service.getPrice())
                .specialty(service.getSpecialty())
                .hospital(HospitalDTO.from(service.getHospital()))
                .build();
    }
}
