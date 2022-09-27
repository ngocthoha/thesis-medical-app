package com.thesis.medicalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private String firstName;
    private String lastName;
    private String country;
    private String province;
    private String town;
    private String commune;
    private String detailedAddress;
    private String phone;
    private Date dob;
    private String job;
    private String identityCard;
    private String healthInsurance;
    private String folk;
    private Gender gender;
    private String guardian;
    private String guardianPhone;
    private String guardianIdentityCard;
    private String relationship;
    private String relationshipWithPatient;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
