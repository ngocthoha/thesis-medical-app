package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicines")
public class Medicine extends AuditLog {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private StoreMedicine storeMedicine;
    private String unit;
    private String numMedicineInMorning;
    private String numMedicineInAfternoon;
    private String numMedicineInEvening;
    private String dateStart;
    private String dateEnd;
}
