package com.thesis.medicalapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.medicalapp.models.Appointment;
import com.thesis.medicalapp.models.File;
import com.thesis.medicalapp.models.Medicine;
import com.thesis.medicalapp.models.Record;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.payload.response.MessageResponse;
import com.thesis.medicalapp.pojo.RecordDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.services.FileService;
import com.thesis.medicalapp.services.MedicineService;
import com.thesis.medicalapp.services.RecordService;
import com.thesis.medicalapp.utils.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final MedicineService medicineService;
    private final AppointmentRepository appointmentRepository;
    private final FileService fileService;
    @PostMapping(value = "/records", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse> saveRecord(
            @RequestParam("appointmentId") String appointmentId,
            @RequestParam(name = "pathological", required = false) String pathological,
            @RequestParam(name = "personalMedicalHistory", required = false) String personalMedicalHistory,
            @RequestParam(name = "familyMedicalHistory", required = false) String familyMedicalHistory,
            @RequestParam(name = "bodyInspection", required = false) String bodyInspection,
            @RequestParam(name = "bloodVessel", required = false) String bloodVessel,
            @RequestParam(name = "temperature", required = false) String temperature,
            @RequestParam(name = "bloodPressure", required = false) String bloodPressure,
            @RequestParam(name = "heartbeat", required = false) String heartbeat,
            @RequestParam(name = "summary", required = false) String summary,
            @RequestParam(name = "partsInspection", required = false) String partsInspection,
            @RequestParam(name = "hospitalize", required = false) Boolean hospitalize,
            @RequestParam(name = "facultyTreatment", required = false) String facultyTreatment,
            @RequestPart(name = "medicines", required = false) String medicines,
            @RequestParam(name = "notes", required = false) String notes,
            @RequestParam(name = "reExaminationDate", required = false) String reExaminationDate,
            @RequestParam(name = "files", required = false) MultipartFile[] files
    ) {
        try {
            Record record = new Record();
            Appointment appointment = appointmentRepository.findAppointmentById(appointmentId);
            record.setAppointment(appointment);
            SequenceGenerator sequenceGenerator = new SequenceGenerator();
            Long recordId = sequenceGenerator.nextId();
            record.setRecordId(recordId);
            record.setPathological(pathological);
            record.setPersonalMedicalHistory(personalMedicalHistory);
            record.setFamilyMedicalHistory(familyMedicalHistory);
            record.setBodyInspection(bodyInspection);
            record.setBloodVessel(bloodVessel);
            record.setTemperature(temperature);
            record.setBloodPressure(bloodPressure);
            record.setHeartbeat(heartbeat);
            record.setSummary(summary);
            record.setPartsInspection(partsInspection);
            record.setHospitalize(hospitalize);
            record.setFacultyTreatment(facultyTreatment);
            record.setMedicines(new ArrayList<>());
            if (null != medicines) {
                ObjectMapper mapper = new ObjectMapper();
                Medicine[] medicinesMapper = mapper.readValue(medicines, Medicine[].class);
                Arrays.asList(medicinesMapper).stream().forEach(m -> {
                    Medicine medicine = medicineService.saveMedicine(m);
                    record.getMedicines().add(medicine);
                });
            }
            record.setNotes(notes);
            record.setReExaminationDate(new ArrayList<>());
            if (null != reExaminationDate) {
                Date dateFormat = new Date();
                try {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(reExaminationDate);
                    record.getReExaminationDate().add(dateFormat);
                    appointment.setDate(dateFormat);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            record.setFiles(new ArrayList<>());
            if (null != files) {
                Arrays.asList(files).stream().forEach(file -> {
                    try {
                        File File = fileService.store(file);
                        record.getFiles().add(File);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            Date createdDate = new Date();
            record.setCreatedDate(createdDate);
            RecordDTO recordDTO = recordService.saveRecord(record);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", recordDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }

    @GetMapping("/records/all")
    public ResponseEntity<ApiResponse> getRecords() {
        try {
            List<RecordDTO> recordDTOS = recordService.getRecords();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", recordDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
}
