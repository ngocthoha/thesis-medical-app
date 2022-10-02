package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private ScheduleType type;
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne
    private Room room;
    @ElementCollection
    private List<String> times;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
