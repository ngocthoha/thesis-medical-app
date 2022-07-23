package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.DoctorDTO;
import com.thesis.medicalapp.pojo.UserDoctorDTO;
import com.thesis.medicalapp.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/doctors/all")
    public ResponseEntity<ApiResponse> getDoctors() {
        try {
            List<UserDoctorDTO> doctors = doctorService.getDoctors();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", doctors)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @GetMapping("/doctors/specialty")
    public ResponseEntity<ApiResponse> getDoctorsBySpecialty(@RequestParam String specialty) {
        try {
            List<UserDoctorDTO> doctors = doctorService.getDoctorsBySpecialty(specialty);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", doctors)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
}
