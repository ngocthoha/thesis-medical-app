package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.exception.ApiRequestException;
import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.payload.AppointmentRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.payload.response.MessageResponse;
import com.thesis.medicalapp.pojo.AppointmentDTO;
import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.repository.*;
import com.thesis.medicalapp.services.*;
import com.thesis.medicalapp.utils.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final ProfileService profileService;
    private final DoctorService doctorService;
    private final FileService fileService;
    private final RoomRepository roomRepository;
    private final MedicalFileRepository medicalFileRepository;

    @PostMapping("/appointments")
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        Profile profile = profileService.findProfileById(appointmentRequest.getProfileId());
        if (profile == null) throw new ApiRequestException("Could not find profile!");
        appointment.setProfile(profile);
        Doctor doctor = doctorService.findDoctorById(appointmentRequest.getDoctorId());
        if (doctor == null) throw new ApiRequestException("Could not find doctor!");
        appointment.setDoctor(doctor);

        Date dateFormat = new Date();
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(appointmentRequest.getDate());
        } catch (Exception e) {
            throw new ApiRequestException("Invalid date!");
        }
        appointment.setDate(dateFormat);
        Room room = roomRepository.findRoomById(appointmentRequest.getRoomId());
        if (room == null) throw new ApiRequestException("Could not find room!");
        appointment.setRoom(room);
        appointment.setTime(appointmentRequest.getTime());
        appointment.setSymptom(appointmentRequest.getSymptom());
        appointment.setFiles(new ArrayList<>());
        if (null != appointmentRequest.getFiles()) {
            for (MedicalFile file : appointmentRequest.getFiles()) {
                MedicalFile File = medicalFileRepository.save(file);
                appointment.getFiles().add(File);
            }
        }
        appointment.setStatus(Status.ACTIVE);
        appointment.setType(appointmentRequest.getType());
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        Long code = sequenceGenerator.nextId();
        appointment.setCode(code);
        AppointmentDTO appointmentDTO = appointmentService.saveAppointment(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(appointmentDTO)
        );
    }
    @GetMapping("/appointments")
    public ResponseEntity<ApiResponse> getAppointmentByProfileId(@RequestParam("profileId") String profileId) {
        List<AppointmentDTO> appointmentDTOS = appointmentService.getAppointmentByProfileId(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(appointmentDTOS)
        );
    }
    @PatchMapping("/appointments")
    public ResponseEntity<ApiResponse> updateAppointment(
            @RequestParam("id") String id,
            @RequestParam(name = "room", required = false) Room room,
            @RequestParam("profileId") String profileId,
            @RequestParam("doctorId") String doctorId,
            @RequestParam("date") Date date,
            @RequestParam("time") String time,
            @RequestParam(name = "symptom", required = false) String symptom,
            @RequestParam(name = "files", required = false) MultipartFile[] files,
            @RequestParam(name = "deleteFile", required = false) String fileId,
            @RequestParam(name = "status", required = false) Status status
    ) {
        Appointment appointment = appointmentService.findAppointmentById(id);
        Profile profile = profileService.findProfileById(profileId);
        Doctor doctor = doctorService.findDoctorById(doctorId);
        appointment.setRoom(room);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setProfile(profile);
        appointment.setDoctor(doctor);
        appointment.setSymptom(symptom);
        appointment.setStatus(status);
//        if (null != files) {
//            Arrays.asList(files).stream().forEach(file -> {
//                try {
//                    File File = fileService.store(file);
//                    appointment.getFiles().add(File);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        if (null != fileId) {
//            fileService.removeFile(fileId);
//        }
        appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok(
                new ApiResponse(null)
        );
    }
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<ApiResponse> removeAppointment(@PathVariable String id) {
        int result = appointmentService.removeAppointment(id);
        return ResponseEntity.ok(
                new ApiResponse(result, "", null)
        );
    }

    @GetMapping("/appointments/doctor")
    public ResponseEntity<ApiResponse> getAppointmentsByDateAndDoctor(@RequestParam String date) {
        try {
            List<Object> list = appointmentService.getAppointmentsByDateAndDoctor(date);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", list)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @GetMapping("/doctor/appointments/schedule")
    public ResponseEntity<ApiResponse> getAllByDateIsBetweenAndDoctor(@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd) {
        Date DateStart = new Date();
        try {
            DateStart = new SimpleDateFormat("yyyy-MM-dd").parse(dateStart);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Date DateEnd = new Date();
        try {
            DateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<?> appointment = appointmentService.getAllByDateIsBetweenAndDoctor(DateStart, DateEnd);
        return ResponseEntity.ok(
                new ApiResponse(1, "", appointment)
        );
    }
}
