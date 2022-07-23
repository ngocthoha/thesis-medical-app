package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor extends User{
    private String specialty;
    private String level;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    private String bio;
    private Integer registrationNumber = 0;
    private String price;

    public Doctor(String id, String name, String username, String password, String email, String address, String phone, Date dob, Collection<Role> roles, String imageLink, String specialty, String level, Hospital hospital) {
        super(id, name, username, password, email, address, phone, dob, roles, imageLink);
        this.specialty = specialty;
        this.level = level;
        this.hospital = hospital;
    }
}
