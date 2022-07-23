package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile extends AuditLog{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Long profileNumber;
    private String name;
    private String address;
    private String phone;
    private Date dob;
    private String job;
    private String identityCard;
    private String healthInsurance;
    private String folk;
    private String gender;
    private String guardian;
    private String guardianPhone;
    private String guardianIdentityCard;
    private String relationship;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
}
