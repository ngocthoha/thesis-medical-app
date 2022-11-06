package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.StoreMedicine;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.DoctorDTO;
import com.thesis.medicalapp.pojo.UserDoctorDTO;
import com.thesis.medicalapp.search.SearchRequest;
import com.thesis.medicalapp.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/all")
    public ResponseEntity<Object> getDoctors() {
        List<UserDoctorDTO> doctors = doctorService.getDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(doctors)
        );
    }
    @GetMapping("/specialty")
    public ResponseEntity<Object> getDoctorsBySpecialty(@RequestParam String specialty) {
        List<UserDoctorDTO> doctors = doctorService.getDoctorsBySpecialty(specialty);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(doctors)
        );
    }

    @GetMapping("/hospital")
    public ResponseEntity<Object> getDoctorsByHospital(@RequestParam String hospitalId) {
        List<UserDoctorDTO> doctors = doctorService.getDoctorsByHospital(hospitalId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(doctors)
        );
    }

    @PostMapping("/search")
    public ResponseEntity<Object> search(@RequestBody SearchRequest request) {
        Page<Doctor> page = doctorService.search(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(page.getContent(), page.getPageable())
        );
    }
}
