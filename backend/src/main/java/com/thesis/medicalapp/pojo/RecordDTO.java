package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.models.Record;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Date;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {
    private String id;
    private Long recordId;
    private AppointmentDTO appointment;
    private String pathological;
    private String personalMedicalHistory;
    private String familyMedicalHistory;
    private String bodyInspection;
    private String bloodVessel;
    private String temperature;
    private String bloodPressure;
    private String heartbeat;
    private String summary;
    private String partsInspection;
    private Boolean hospitalize;
    private String facultyTreatment;
    private Collection<Medicine> medicines;
    private String notes;
    private Date reExaminationDate;
    private Collection<File> files;
    private Date createdDate;

    public static RecordDTO from(Record record) {
        return RecordDTO.builder()
                .id(record.getId())
                .recordId(record.getRecordId())
                .appointment(AppointmentDTO.from(record.getAppointment()))
                .pathological(record.getPathological())
                .personalMedicalHistory(record.getPersonalMedicalHistory())
                .familyMedicalHistory(record.getFamilyMedicalHistory())
                .bodyInspection(record.getBodyInspection())
                .bloodVessel(record.getBloodVessel())
                .temperature(record.getTemperature())
                .bloodPressure(record.getBloodPressure())
                .heartbeat(record.getHeartbeat())
                .summary(record.getSummary())
                .partsInspection(record.getPartsInspection())
                .hospitalize(record.getHospitalize())
                .facultyTreatment(record.getFacultyTreatment())
                .medicines(record.getMedicines())
                .notes(record.getNotes())
                .reExaminationDate(record.getReExaminationDate())
                .files(record.getFiles())
                .createdDate(record.getCreatedDate())
                .build();
    }
}
