package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.payload.ServiceRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.pojo.ServiceDTO;
import com.thesis.medicalapp.services.ServiceESService;
import com.thesis.medicalapp.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final ServiceESService serviceESService;

    @PostMapping("")
    public ResponseEntity<Object> saveHospitalService(@RequestBody @Valid ServiceRequest ServiceRequest) {
        HospitalService hospitalService = serviceService.save(ServiceRequest);
        // save service to ES
        serviceESService.save(hospitalService);
        ServiceDTO serviceResponse = ServiceDTO.from(hospitalService);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(serviceResponse)
        );
    }
    @GetMapping("")
    public ResponseEntity<Object> getHospitalServices() {
        Iterable<HospitalService> services = serviceService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(services)
        );
    }

    @GetMapping("/hospital")
    public ResponseEntity<Object> getServicesByHospital(@RequestParam String hospitalId) {
        List<ServiceDTO> services = serviceService.getServicesByHospital(hospitalId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(services)
        );
    }
}
