package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicines")
@ToString
public class Medicine {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String imageUrl;
    private String price;
    private String info;
    private Integer registrationNumber = 0;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
