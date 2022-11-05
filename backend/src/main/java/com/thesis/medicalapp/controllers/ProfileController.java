package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.exception.ApiRequestException;
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
        if (profileService.existsByIdentityCard(profileDTO.getIdentityCard()))
            throw new ApiRequestException("Identity card already exists!");
        ProfileDTO profileResponse = profileService.saveProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(profileResponse)
        );
    }

    @GetMapping("/profiles")
    public ResponseEntity<ApiResponse> getProfilesByUser() {
        List<ProfileDTO> profileDTOS = profileService.getProfilesByUser();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(profileDTOS)
        );
    }

    @GetMapping("/profiles/search")
    public ResponseEntity<ApiResponse> getProfilesByPhone(@RequestParam("phone") String phone) {
        ProfileSearch profileSearch = profileService.searchProfile(phone);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(profileSearch)
        );
    }

    @GetMapping("/profiles/all")
    public ResponseEntity<ApiResponse> getProfiles() {
        List<ProfileDTO> profileDTOS = profileService.getProfiles();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(profileDTOS)
        );
    }

    @PatchMapping("/profiles")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody ProfileDTO profileDTO) {
        profileService.updateProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Success")
        );
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<ApiResponse> removeProfile(@PathVariable String id) {
        profileService.removeProfile(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Success")
        );
    }
}
