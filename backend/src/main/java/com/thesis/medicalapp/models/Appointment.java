package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class Appointment extends AuditLog{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Integer stt;
    @OneToOne
    private Room room;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String time;
    private String symptom;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value= FetchMode.SELECT)
    private Collection<File> files;
    private String status;
    private Boolean isOnline;
}
