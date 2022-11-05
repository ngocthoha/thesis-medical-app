package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.exception.ApiRequestException;
import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.models.Record;
import com.thesis.medicalapp.payload.RecordRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.RecordDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.repository.MedicalFileRepository;
import com.thesis.medicalapp.services.MedicineService;
import com.thesis.medicalapp.services.RecordService;
import com.thesis.medicalapp.utils.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final MedicineService prescribedMedicineService;
    private final AppointmentRepository appointmentRepository;
    private final MedicalFileRepository medicalFileRepository;
    @PostMapping(value = "/records")
    public ResponseEntity<ApiResponse> saveRecord(@RequestBody @Valid RecordRequest recordRequest) {
        Record record = new Record();
        Appointment appointment = appointmentRepository.findAppointmentById(recordRequest.getAppointmentId());
        if (appointment == null) throw new ApiRequestException("Could not find appointment!");
        record.setAppointment(appointment);
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        Long recordId = sequenceGenerator.nextId();
        record.setRecordId(recordId);
        record.setDiagnose(recordRequest.getDiagnose());
        record.setPrescribe(recordRequest.getPrescribe());
        record.setMedicines(new ArrayList<>());
        record.setReExaminationDate(new ArrayList<>());
        if (null != recordRequest.getFiles()) {
            for (MedicalFile file : recordRequest.getFiles()) {
                MedicalFile File = medicalFileRepository.save(file);
                appointment.getFiles().add(File);
            }
        }
        if (null != recordRequest.getReExaminationDate()) {
            Date dateFormat = new Date();
            try {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(recordRequest.getReExaminationDate());
                record.getReExaminationDate().add(dateFormat);
                appointment.setDate(dateFormat);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        RecordDTO recordDTO = recordService.saveRecord(record);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(recordDTO)
        );
    }

    @GetMapping("/records/all")
    public ResponseEntity<ApiResponse> getAllRecords() {
        List<RecordDTO> recordDTOS = recordService.getRecords();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(recordDTOS)
        );
    }

    @GetMapping("/user/records")
    public ResponseEntity<ApiResponse> getRecordsByProfile(@RequestParam String profileId) {
        List<RecordDTO> recordDTOS = recordService.getRecordsByProfile(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(recordDTOS)
        );
    }
}
