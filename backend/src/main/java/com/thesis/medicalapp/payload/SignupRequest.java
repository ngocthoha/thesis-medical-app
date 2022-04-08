package com.thesis.medicalapp.payload;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SignupRequest {
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final String address;
    private final String phoneNumber;
    private final String dob;
    private final String specialty;
    private final String level;
    private final String role;
}
