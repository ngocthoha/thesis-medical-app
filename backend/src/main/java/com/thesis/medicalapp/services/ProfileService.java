package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Profile;

import java.util.List;

public interface ProfileService {
    Profile saveProfile(Profile profile);
    List<Profile> getProfiles();
}
