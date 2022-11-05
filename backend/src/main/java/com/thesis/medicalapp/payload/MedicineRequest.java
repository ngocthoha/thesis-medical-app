package com.thesis.medicalapp.payload;

import lombok.Data;

@Data
public class MedicineRequest {
    private String id;
    private String medicineId;
    private String unit;
    private String numMedicineInMorning;
    private String numMedicineInAfternoon;
    private String numMedicineInEvening;
    private String dateStart;
    private String dateEnd;
}
