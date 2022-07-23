package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Service;
import com.thesis.medicalapp.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
}
