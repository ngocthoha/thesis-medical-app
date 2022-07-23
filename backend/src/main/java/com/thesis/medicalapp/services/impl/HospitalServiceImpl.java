package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.repository.HospitalRepository;
import com.thesis.medicalapp.services.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
}
