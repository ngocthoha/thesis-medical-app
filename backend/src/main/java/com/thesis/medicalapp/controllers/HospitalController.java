package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

}
