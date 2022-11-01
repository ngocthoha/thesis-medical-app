package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {
    private Integer id;
    private String name;
    private String imageUrl;
    private String price;
    private String info;
    private Integer registrationNumber;
    private HospitalDTO hospital;

    public static MedicineDTO from(Medicine medicine) {
        return MedicineDTO.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .imageUrl(medicine.getImageUrl())
                .price(medicine.getPrice())
                .info(medicine.getInfo())
                .registrationNumber(medicine.getRegistrationNumber())
                .hospital(HospitalDTO.from(medicine.getHospital()))
                .build();
    }
}
