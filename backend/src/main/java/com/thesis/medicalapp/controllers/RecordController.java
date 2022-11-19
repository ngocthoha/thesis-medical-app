package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.exception.ApiRequestException;
import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.models.Record;
import com.thesis.medicalapp.payload.MedicineRequest;
import com.thesis.medicalapp.payload.RecordRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.RecordDTO;
import com.thesis.medicalapp.pojo.UserDoctorDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.repository.MedicalFileRepository;
import com.thesis.medicalapp.repository.MedicineRepository;
import com.thesis.medicalapp.search.SearchRequest;
import com.thesis.medicalapp.services.MedicineService;
import com.thesis.medicalapp.services.RecordService;
import com.thesis.medicalapp.utils.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final AppointmentRepository appointmentRepository;
    private final MedicalFileRepository medicalFileRepository;
    private final MedicineService medicineService;

    @PostMapping(value = "/records")
    public ResponseEntity<ApiResponse> saveRecord(@RequestBody @Valid RecordRequest recordRequest) {
        Record record = new Record();
        Appointment appointment = appointmentRepository.findAppointmentById(recordRequest.getAppointmentId());
        if (appointment == null) throw new ApiRequestException("Không tìm thấy lịch hẹn!");
        record.setAppointment(appointment);
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        Long recordId = sequenceGenerator.nextId();
        record.setRecordId(recordId);
        record.setDiagnose(recordRequest.getDiagnose());
        record.setPrescribe(recordRequest.getPrescribe());
        record.setNote(recordRequest.getNote());
        record.setMedicines(new ArrayList<>());
        if (null != recordRequest.getMedicines()) {
            System.out.println("save medicine!");
            for (MedicineRequest medicineRequest: recordRequest.getMedicines()) {
                Medicine m = medicineService.save(medicineRequest);
                record.getMedicines().add(m);
            }
        }
        record.setFiles(new ArrayList<>());
        if (null != recordRequest.getFiles()) {
            for (MedicalFile file : recordRequest.getFiles()) {
                MedicalFile File = medicalFileRepository.save(file);
                record.getFiles().add(File);
            }
        }

        record.setReExaminationDate(new ArrayList<>());
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
        appointment.setStatus(Status.COMPLETE);
        appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(recordDTO)
        );
    }

    @GetMapping("/records")
    public ResponseEntity<Object> getAllRecords(
            @RequestParam Integer size,
            @RequestParam Integer page
    ) {
        SearchRequest request = new SearchRequest();
        request.setFilters(new ArrayList<>());
        request.setSorts(new ArrayList<>());
        request.setPage(page);
        request.setSize(size);
        Page<Record> records = recordService.findAll(request);
        Page<RecordDTO> recordDTOS = records.map(
                record -> {
                    RecordDTO recordDTO = RecordDTO.from(record);
                    return recordDTO;
                }
        );
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(recordDTOS.getContent(), recordDTOS)
        );
    }

    @GetMapping("/user/records")
    public ResponseEntity<ApiResponse> getRecordsByProfile(@RequestParam String profileId) {
        List<RecordDTO> recordDTOS = recordService.getRecordsByProfile(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(recordDTOS)
        );
    }

    @GetMapping("/appointments/record")
    public ResponseEntity<ApiResponse> getRecordsByAppointment(@RequestParam String appointmentId) {
        RecordDTO recordDTO = recordService.getRecordByAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(recordDTO)
        );
    }
}
