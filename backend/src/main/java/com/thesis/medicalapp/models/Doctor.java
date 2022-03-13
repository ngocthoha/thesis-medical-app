package com.thesis.medicalapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "doctors")
public class Doctor extends User{
    private String specialty;
}
