package com.thesis.medicalapp.services;

import com.thesis.medicalapp.pojo.ProfileDTO;

import java.util.List;

public interface ProfileService {
    ProfileDTO saveProfile(ProfileDTO profileDTO);
    List<ProfileDTO> getProfiles();
    int updateProfile(ProfileDTO profileDTO);
    int removeProfile(String id);
}
