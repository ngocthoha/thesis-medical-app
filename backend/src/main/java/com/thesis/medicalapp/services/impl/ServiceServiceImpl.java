package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.repository.ServiceRepository;
import com.thesis.medicalapp.services.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
}
