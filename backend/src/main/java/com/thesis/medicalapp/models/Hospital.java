package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospitals")
public class Hospital extends AuditLog {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @OneToOne
    private Address address;
    @Lob
    private String info;
    private Integer registrationNumber;
    private String mapImageUrl;
    private String hospitalImageUrl;
    private Boolean isActive;
    @OneToOne
    private HospitalHour hospitalHour;

    public Hospital(String id) {
        this.id = id;
    }
}
