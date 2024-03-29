package com.thesis.medicalapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSearch {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
}
