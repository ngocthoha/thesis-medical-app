package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.payload.ServiceRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.pojo.ServiceDTO;
import com.thesis.medicalapp.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @PostMapping("")
    public ResponseEntity<Object> saveHospitalService(@RequestBody @Valid ServiceRequest ServiceRequest) {
        ServiceDTO serviceResponse = serviceService.saveService(ServiceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(serviceResponse)
        );
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getHospitalServices() {
        List<ServiceDTO> services = serviceService.getServices();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(services)
        );
    }

    @GetMapping("")
    public ResponseEntity<Object> getServicesByHospital(@RequestParam String hospitalId) {
        List<ServiceDTO> services = serviceService.getServicesByHospital(hospitalId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(services)
        );
    }
}
