package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor extends User{
    private String specialty;
    private String level;

    public Doctor(String id, String name, String username, String password, String email, String address, String phoneNumber, Date dob, Collection<Role> roles, String specialty, String level) {
        super(id, name, username, password, email, address, phoneNumber, dob, roles);
        this.specialty = specialty;
        this.level = level;
    }
}
