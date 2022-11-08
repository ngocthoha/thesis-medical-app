package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.payload.ServiceRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.pojo.ServiceDTO;
import com.thesis.medicalapp.services.ServiceESService;
import com.thesis.medicalapp.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

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

    @DeleteMapping("")
    public ResponseEntity<Object> deleteService(@RequestParam String serviceId) {
        serviceService.delete(serviceId);
        // Delete service to ES
        serviceESService.delete(serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(null)
        );
    }

    @PutMapping("")
    public ResponseEntity<Object> updateService(@RequestBody HospitalService serviceRequest) {
        HospitalService hospitalService = serviceService.update(serviceRequest);
        // Update service to ES
        serviceESService.save(hospitalService);
        ServiceDTO serviceResponse = ServiceDTO.from(hospitalService);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(serviceResponse)
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
