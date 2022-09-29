package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.payload.response.ProfileSearch;
import com.thesis.medicalapp.pojo.ProfileDTO;
import com.thesis.medicalapp.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @PostMapping("/profiles")
    public ResponseEntity<ApiResponse> saveProfile(@RequestBody @Valid ProfileDTO profileDTO) {
        if (profileService.existsByIdentityCard(profileDTO.getIdentityCard())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, "Identity card is already taken!", null)
            );
        }
        System.out.println("save profile");
        ProfileDTO profileResponse = profileService.saveProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(1, "Success", profileResponse)
        );
    }
    @GetMapping("/profiles")
    public ResponseEntity<ApiResponse> getProfilesByUser() {
        try {
            List<ProfileDTO> profileDTOS = profileService.getProfilesByUser();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", profileDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @GetMapping("/profiles/search")
    public ResponseEntity<ApiResponse> getProfilesByPhone(@RequestParam("phone") String phone) {
        try {
            ProfileSearch profileSearch = profileService.searchProfile(phone);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", profileSearch)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }

    @GetMapping("/profiles/all")
    public ResponseEntity<ApiResponse> getProfiles() {
        try {
            List<ProfileDTO> profileDTOS = profileService.getProfiles();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", profileDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }

    @PatchMapping("/profiles")
    public ResponseEntity<ApiResponse> updateProfile(ProfileDTO profileDTO) {
        try {
            profileService.updateProfile(profileDTO);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<ApiResponse> removeProfile(@PathVariable String id) {
        try {
            System.out.println("remove profile");
            profileService.removeProfile(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
}
