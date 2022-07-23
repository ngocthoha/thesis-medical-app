package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
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

    public static ProfileDTO from(Profile profile) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .profileNumber(profile.getProfileNumber())
                .name(profile.getName())
                .address(profile.getAddress())
                .phone(profile.getPhone())
                .dob(profile.getDob())
                .job(profile.getJob())
                .identityCard(profile.getIdentityCard())
                .healthInsurance(profile.getHealthInsurance())
                .folk(profile.getFolk())
                .gender(profile.getGender())
                .guardian(profile.getGuardian())
                .guardianPhone(profile.getGuardianPhone())
                .guardianIdentityCard(profile.getGuardianIdentityCard())
                .relationship(profile.getRelationship())
                .build();
    }
}
