package com.thesis.medicalapp.services;

import com.thesis.medicalapp.payload.ServiceRequest;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.pojo.ServiceDTO;

import java.util.List;

public interface ServiceService {
    ServiceDTO saveService(ServiceRequest serviceRequest);
    List<ServiceDTO> getServices();
}
