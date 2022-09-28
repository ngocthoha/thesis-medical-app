package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Gender;
import com.thesis.medicalapp.models.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String id;
    private Long profileNumber;
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotEmpty
    @NotBlank
    private String lastName;
    @NotEmpty
    @NotBlank
    private String country;
    @NotEmpty
    @NotBlank
    private String province;
    @NotEmpty
    @NotBlank
    private String town;
    @NotEmpty
    @NotBlank
    private String commune;
    @NotEmpty
    @NotBlank
    private String detailedAddress;
    @NotEmpty
    @NotBlank
    private String phone;
    @Email(message = "ivalid email address")
    private String email;
    @NotNull
    private Date dob;
    private String job;
    @NotEmpty
    @NotBlank
    private String identityCard;
    private String healthInsurance;
    @NotEmpty
    @NotBlank
    private String folk;
    @NotNull
    private Gender gender;
    private String guardian;
    private String guardianPhone;
    private String guardianIdentityCard;
    private String relationship;
    private String relationshipWithPatient;

    public static ProfileDTO from(Profile profile) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .profileNumber(profile.getProfileNumber())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .country(profile.getCountry())
                .province(profile.getProvince())
                .town(profile.getTown())
                .commune(profile.getCommune())
                .detailedAddress(profile.getDetailedAddress())
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
                .relationshipWithPatient(profile.getRelationshipWithPatient())
                .build();
    }
}
