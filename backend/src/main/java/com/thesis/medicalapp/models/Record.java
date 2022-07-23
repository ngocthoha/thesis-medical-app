package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private Long recordId;
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
    @OneToMany
    private Collection<Medicine> medicines;
    private String notes;
    @ElementCollection
    private List<Date> reExaminationDate;
    @OneToMany
    private Collection<File> files;
    private Date createdDate;
}
