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
public class Doctor extends User {
    @Column(nullable = false)
    private String name;
    private Gender gender;
    private Date dob;
    private String email;
    private String specialty;
    private String level;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    private String bio;
    private Integer registrationNumber = 0;
    private String price;

    public Doctor(String id, String username, String phone, String password, Boolean enabled, String imageUrl, Collection<Role> roles, String name, Gender gender, Date dob, String email,  String specialty, String level, String bio, String price, Hospital hospital) {
        super(id, username, phone, password, enabled, imageUrl, roles);
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.specialty = specialty;
        this.level = level;
        this.bio = bio;
        this.price = price;
        this.hospital = hospital;
    }
}
