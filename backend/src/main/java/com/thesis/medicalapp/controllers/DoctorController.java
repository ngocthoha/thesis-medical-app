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
    public ResponseEntity<Object> getDoctors() {
        List<UserDoctorDTO> doctors = doctorService.getDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Success", doctors)
        );
    }
    @GetMapping("/doctors/specialty")
    public ResponseEntity<Object> getDoctorsBySpecialty(@RequestParam String specialty) {
        List<UserDoctorDTO> doctors = doctorService.getDoctorsBySpecialty(specialty);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Success", doctors)
        );
    }

    @GetMapping("/doctors/hospital")
    public ResponseEntity<Object> getDoctorsByHospital(@RequestParam String hospitalId) {
        List<UserDoctorDTO> doctors = doctorService.getDoctorsByHospital(hospitalId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Success", doctors)
        );
    }
}
