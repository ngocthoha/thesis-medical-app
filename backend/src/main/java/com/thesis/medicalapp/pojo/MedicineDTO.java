package com.thesis.medicalapp.pojo;

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
    private Integer number;
    private String type;
    private String use;

    public static MedicineDTO from(Medicine medicine) {
        return MedicineDTO.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .number(medicine.getNumber())
                .type(medicine.getType())
                .use(medicine.getType())
                .build();
    }
}
