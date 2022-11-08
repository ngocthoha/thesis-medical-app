package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.payload.ServiceRequest;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.pojo.ServiceDTO;

import java.util.List;

public interface ServiceService {
    HospitalService save(ServiceRequest serviceRequest);
    HospitalService update(HospitalService hospitalService);
    Iterable<HospitalService> getAll();
    void delete(String id);
    List<ServiceDTO> getServicesByHospital(String hospitalId);
}
