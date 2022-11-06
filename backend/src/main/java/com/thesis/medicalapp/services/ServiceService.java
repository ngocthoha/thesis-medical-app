package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.payload.ServiceRequest;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.pojo.ServiceDTO;

import java.util.List;

public interface ServiceService {
    HospitalService save(ServiceRequest serviceRequest);
    Iterable<HospitalService> getAll();
    List<ServiceDTO> getServicesByHospital(String hospitalId);
}
