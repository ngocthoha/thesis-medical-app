package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.payload.response.ProfileSearch;
import com.thesis.medicalapp.pojo.ProfileDTO;

import java.util.List;

public interface ProfileService {
    ProfileDTO saveProfile(ProfileDTO profileDTO);
    List<ProfileDTO> getProfilesByUser();
    ProfileSearch searchProfile(String phone);
    List<ProfileDTO> getProfiles();
    void updateProfile(ProfileDTO profileDTO);
    void removeProfile(String id);
    Profile findProfileById(String id);
    Boolean existsByIdentityCard(String ic);
}
