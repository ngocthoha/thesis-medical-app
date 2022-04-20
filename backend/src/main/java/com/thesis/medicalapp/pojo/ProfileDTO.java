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
    private String phoneNumber;
    private Date dob;
    private String job;
    private String identityCard;
    private String healthInsurance;
    private String folk;
    private String gender;
    private String protector;

    public static ProfileDTO from(Profile profile) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .profileNumber(profile.getProfileNumber())
                .name(profile.getName())
                .address(profile.getAddress())
                .phoneNumber(profile.getPhoneNumber())
                .dob(profile.getDob())
                .job(profile.getJob())
                .identityCard(profile.getIdentityCard())
                .healthInsurance(profile.getHealthInsurance())
                .folk(profile.getFolk())
                .gender(profile.getGender())
                .protector(profile.getProtector())
                .build();
    }
}
