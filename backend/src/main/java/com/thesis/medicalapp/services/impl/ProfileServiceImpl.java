package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.ThesisMedicalAppApplication;
import com.thesis.medicalapp.models.Global;
import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.models.User;
import com.thesis.medicalapp.payload.response.ProfileSearch;
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
import java.util.Optional;
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
        profile.setPhone(profileDTO.getPhone());
        profile.setJob(profileDTO.getJob());
        profile.setDob(profileDTO.getDob());
        profile.setFolk(profileDTO.getFolk());
        profile.setGender(profileDTO.getGender());
        profile.setIdentityCard(profileDTO.getIdentityCard());
        profile.setHealthInsurance(profileDTO.getHealthInsurance());
        profile.setGuardian(profileDTO.getGuardian());
        profile.setGuardianPhone(profileDTO.getGuardianPhone());
        profile.setGuardianIdentityCard(profileDTO.getGuardianIdentityCard());
        profile.setRelationship(profileDTO.getRelationship());
        Optional<User> userOp = userRepository.findByUsername(Global.user.getUsername());
        User user = userOp.get();
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
        Optional<User> userOp = userRepository.findByUsername(Global.user.getUsername());
        User user = userOp.get();
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
        profile.setPhone(profileDTO.getPhone());
        profile.setIdentityCard(profileDTO.getIdentityCard());
        profile.setHealthInsurance(profileDTO.getHealthInsurance());
        profile.setGender(profileDTO.getGender());
        profile.setAddress(profileDTO.getAddress());
        profile.setDob(profileDTO.getDob());
        profile.setFolk(profileDTO.getFolk());
        profile.setGuardian(profileDTO.getGuardian());
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
    @Override
    public ProfileSearch searchProfile(String phone) {
        System.out.println(phone);
        Optional<Profile> profile = profileRepository.findProfileByPhone(phone);
        if (!profile.isPresent()) {
            System.out.println("no result");
            return null;
        }
        ProfileSearch profileSearch = new ProfileSearch();
        profileSearch.setId(profile.get().getId());
        profileSearch.setPhone(profile.get().getPhone());
        profileSearch.setName(profile.get().getName());
        return profileSearch;
    }
}
