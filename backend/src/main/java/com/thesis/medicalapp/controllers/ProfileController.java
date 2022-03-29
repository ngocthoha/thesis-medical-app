package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.pojo.ProfileDTO;
import com.thesis.medicalapp.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @PostMapping("/profiles")
    public ResponseEntity<ProfileDTO> saveProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO profileResponse = profileService.saveProfile(profileDTO);
        return ResponseEntity.ok().body(profileResponse);
    }
    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileDTO>> getProfiles() {
        return ResponseEntity.ok().body(profileService.getProfiles());
    }

    @PatchMapping("/profiles")
    public ResponseEntity<Integer> updateProfile(ProfileDTO profileDTO) {
        int result = profileService.updateProfile(profileDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Integer> removeProfile(@PathVariable String id) {
        int result = profileService.removeProfile(id);
        return ResponseEntity.ok(result);
    }
}
