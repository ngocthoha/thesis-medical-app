package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.ThesisMedicalAppApplication;
import com.thesis.medicalapp.models.Global;
import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.models.User;
import com.thesis.medicalapp.pojo.ProfileDTO;
import com.thesis.medicalapp.repository.ProfileRepository;
import com.thesis.medicalapp.repository.UserRepository;
import com.thesis.medicalapp.services.ProfileService;
import com.thesis.medicalapp.utils.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileDTO saveProfile(ProfileDTO profileDTO) {
        log.info("Saving new profile of {} to the database", profileDTO.getName());
        Profile profile = new Profile();
        profile.setName(profileDTO.getName());
        profile.setAddress(profileDTO.getAddress());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setJob(profileDTO.getJob());
        profile.setDob(profileDTO.getDob());
        profile.setFolk(profileDTO.getFolk());
        profile.setGender(profileDTO.getGender());
        profile.setIdentityCard(profileDTO.getIdentityCard());
        profile.setHealthInsurance(profileDTO.getHealthInsurance());
        profile.setProtector(profileDTO.getProtector());
        User user = userRepository.findByUsername(Global.user.getUsername());
        profile.setUser(user);
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        Long profileNumber = sequenceGenerator.nextId();
        profile.setProfileNumber(profileNumber);
        Profile profileEntity = profileRepository.save(profile);
        ProfileDTO profileDTOResponse = ProfileDTO.from(profileEntity);
        return profileDTOResponse;
    };
    @Override
    public List<ProfileDTO> getProfilesByUser(){
        User user = userRepository.findByUsername(Global.user.getUsername());
        List<Profile> profiles = profileRepository.findAllByUser(user)
                .stream()
                .collect(Collectors.toList());

        List<ProfileDTO> profileDTOS = profiles.stream().map(p -> {
            ProfileDTO profileDTO = ProfileDTO.from(p);
            return profileDTO;
        }).collect(Collectors.toList());
        return profileDTOS;
    };
    @Override
    public List<ProfileDTO> getProfiles(){
        List<Profile> profiles = profileRepository.findAll()
                .stream()
                .collect(Collectors.toList());

        List<ProfileDTO> profileDTOS = profiles.stream().map(p -> {
            ProfileDTO profileDTO = ProfileDTO.from(p);
            return profileDTO;
        }).collect(Collectors.toList());
        return profileDTOS;
    };
    @Override
    public void updateProfile(ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(profileDTO.getId());
        profile.setName(profileDTO.getName());
        profile.setJob(profileDTO.getJob());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setIdentityCard(profileDTO.getIdentityCard());
        profile.setHealthInsurance(profileDTO.getHealthInsurance());
        profile.setGender(profileDTO.getGender());
        profile.setAddress(profileDTO.getAddress());
        profile.setDob(profileDTO.getDob());
        profile.setFolk(profileDTO.getFolk());
        profile.setProtector(profileDTO.getProtector());
        profileRepository.save(profile);
    }
    @Override
    public void removeProfile(String id) {
        Profile profile = profileRepository.findById(id);
        profileRepository.delete(profile);
    }
    @Override
    public Profile findProfileById(String id) {
        return profileRepository.findById(id);
    }
    @Override
    public Boolean existsByIdentityCard(String ic) {
        return profileRepository.existsByIdentityCard(ic);
    }
}
