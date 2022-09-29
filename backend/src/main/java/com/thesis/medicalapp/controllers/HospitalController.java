package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    @PostMapping("")
    public ResponseEntity<Object> saveHospital(@RequestBody HospitalDTO hospitalDTO) {
        HospitalDTO hospitalResponse = hospitalService.saveHospital(hospitalDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Success", hospitalResponse)
        );
    }
    @GetMapping("")
    public ResponseEntity<ApiResponse> getHospitals() {
        List<HospitalDTO> hospitals = hospitalService.getHospitals();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Success", hospitals)
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getHospitalById(@PathVariable("id") String id) {
        try {
            HospitalDTO hospitalDTO = hospitalService.getHospitalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", hospitalDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
}
