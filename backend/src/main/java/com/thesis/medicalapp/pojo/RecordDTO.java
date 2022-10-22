package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.models.Record;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {
    private String id;
    private Long recordId;
    private AppointmentDTO appointment;
    private String diagnose;
    private String prescribe;
    private Collection<Medicine> medicines;
    private List<Date> reExaminationDate;
    private List<String> clinicalResultImageUrl;

    public static RecordDTO from(Record record) {
        return RecordDTO.builder()
                .id(record.getId())
                .recordId(record.getRecordId())
                .appointment(AppointmentDTO.from(record.getAppointment()))
                .diagnose(record.getDiagnose())
                .prescribe(record.getPrescribe())
                .medicines(record.getMedicines())
                .reExaminationDate(record.getReExaminationDate())
                .clinicalResultImageUrl(record.getClinicalResultImageUrl())
                .build();
    }
}
