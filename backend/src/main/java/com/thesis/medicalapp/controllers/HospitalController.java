package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.StoreMedicine;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.search.SearchRequest;
import com.thesis.medicalapp.services.DoctorESService;
import com.thesis.medicalapp.services.HospitalESService;
import com.thesis.medicalapp.services.HospitalService;
import com.thesis.medicalapp.services.ServiceESService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final HospitalESService hospitalESService;
    private final DoctorESService doctorESService;
    private final ServiceESService serviceESService;

    @PostMapping("")
    public ResponseEntity<Object> saveHospital(@RequestBody HospitalDTO hospitalDTO) {
        Hospital hospital = hospitalService.saveHospital(hospitalDTO);
        // Save hospital to ES
        hospitalESService.save(hospital);
        HospitalDTO hospitalResponse = HospitalDTO.from(hospital);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(hospitalResponse)
        );
    }

    @PutMapping("")
    public ResponseEntity<Object> updateHospital(@RequestBody Hospital hospitalRequest) {
        Hospital hospital = hospitalService.update(hospitalRequest);
        // Update hospital to ES
        hospitalESService.save(hospital);
        HospitalDTO hospitalResponse = HospitalDTO.from(hospital);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(hospitalResponse)
        );
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteHospital(@RequestParam String hospitalId) {
        hospitalService.delete(hospitalId);
        // Delete hospital to ES
        hospitalESService.delete(hospitalId);
        // Delete doctor to ES
        doctorESService.deleteAllByHospitalId(hospitalId);
        // Delete service to ES
        serviceESService.deleteAllByHospitalId(hospitalId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(null)
        );
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getHospitals() {
        List<HospitalDTO> hospitals = hospitalService.getHospitals();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(hospitals)
        );
    }

    @PostMapping("/search")
    public ResponseEntity<Object> search(@RequestBody SearchRequest request) {
        Page<Hospital> page = hospitalService.search(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(page.getContent(), page)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getHospitalById(@PathVariable("id") String id) {
        try {
            HospitalDTO hospitalDTO = hospitalService.getHospitalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(hospitalDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage())
            );
        }
    }
}
