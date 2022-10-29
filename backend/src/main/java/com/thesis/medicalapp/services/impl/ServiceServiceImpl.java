package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.exception.ApiRequestException;
import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.payload.ServiceRequest;
import com.thesis.medicalapp.pojo.ServiceDTO;
import com.thesis.medicalapp.repository.HospitalRepository;
import com.thesis.medicalapp.repository.ServiceRepository;
import com.thesis.medicalapp.services.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final HospitalRepository hospitalRepository;
    @Override
    public ServiceDTO saveService(ServiceRequest serviceRequest) {
        HospitalService hospitalService = new HospitalService();
        Optional<Hospital> hospital = hospitalRepository.findById(serviceRequest.getHospitalId());
        if (!hospital.isPresent()) throw new ApiRequestException("Không tìm thấy bệnh viện!");
        hospitalService.setHospital(hospital.get());
        hospitalService.setInfo(serviceRequest.getInfo());
        hospitalService.setName(serviceRequest.getName());
        hospitalService.setPrice(serviceRequest.getPrice());
        hospitalService.setRegistrationNumber(0);
        hospitalService.setNumOfServicePerHour(serviceRequest.getNumOfServicePerHour());
        hospitalService.setType(serviceRequest.getType());
        hospitalService.setSpecialty(serviceRequest.getSpecialty());
        hospitalService.setServiceImageUrl(serviceRequest.getServiceImageUrl());
        HospitalService hospitalServiceDB = serviceRepository.save(hospitalService);
        ServiceDTO service_response = ServiceDTO.from(hospitalServiceDB);
        return service_response;
    }

    @Override
    public List<ServiceDTO> getServices() {
        List<HospitalService> hospitalServices = serviceRepository.findAll()
                .stream().collect(Collectors.toList());
        List<ServiceDTO> serviceDTOS = hospitalServices.stream().map(d -> {
            ServiceDTO serviceDTO = ServiceDTO.from(d);
            return serviceDTO;
        }).collect(Collectors.toList());
        return serviceDTOS;
    }

    @Override
    public List<ServiceDTO> getServicesByHospital(String hospitalId) {
        List<HospitalService> hospitalServices = serviceRepository.findByHospitalId(hospitalId)
                .stream().collect(Collectors.toList());
        List<ServiceDTO> serviceDTOS = hospitalServices.stream().map(d -> {
            ServiceDTO serviceDTO = ServiceDTO.from(d);
            return serviceDTO;
        }).collect(Collectors.toList());
        return serviceDTOS;
    }
}
