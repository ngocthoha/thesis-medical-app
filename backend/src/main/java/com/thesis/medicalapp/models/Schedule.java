package com.thesis.medicalapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String room;
    @ElementCollection
    private List<String> times;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
