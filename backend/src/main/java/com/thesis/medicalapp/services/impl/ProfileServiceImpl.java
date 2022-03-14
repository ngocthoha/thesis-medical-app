package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.repository.ProfileRepository;
import com.thesis.medicalapp.services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    @Override
    public Profile saveProfile(Profile profile) {
        String profileId = UUID.randomUUID().toString();
        profile.setProfileId(profileId);
        return profileRepository.save(profile);
    };
    @Override
    public List<Profile> getProfiles(){
        return profileRepository.findAll();
    };
}
