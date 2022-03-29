package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Appointment;
import com.thesis.medicalapp.models.Doctor;
import com.thesis.medicalapp.models.File;
import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.payload.response.MessageResponse;
import com.thesis.medicalapp.pojo.AppointmentDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.repository.DoctorRepository;
import com.thesis.medicalapp.repository.ProfileRepository;
import com.thesis.medicalapp.services.AppointmentService;
import com.thesis.medicalapp.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final ProfileRepository profileRepository;
    private final DoctorRepository doctorRepository;

    private final FileService fileService;

    @PostMapping("/appointments")
    public ResponseEntity<Object> saveAppointment(
            @RequestParam("profileId") String profileId,
            @RequestParam("doctorId") String doctorId,
            @RequestParam("date") Date date,
            @RequestParam("time") String time,
            @RequestParam(name = "symptom", required = false) String symptom,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "timeSituation", required = false) String timeSituation,
            @RequestParam(name = "files", required = false) MultipartFile[] files
    ) {
            try {
                Appointment appointment = new Appointment();
                Profile profile = profileRepository.findById(profileId);
                Doctor doctor = doctorRepository.findDoctorById(doctorId);
                appointment.setProfile(profile);
                appointment.setDoctor(doctor);
                appointment.setStt(1);
                appointment.setRoom("H2");
                appointment.setDate(date);
                appointment.setTime(time);
                appointment.setSymptom(symptom);
                appointment.setDescription(description);
                appointment.setTimeSituation(timeSituation);
                appointment.setFiles(new ArrayList<>());
                if (null != files) {
                    Arrays.asList(files).stream().forEach(file -> {
                        try {
                            File File = fileService.store(file);
                            appointment.getFiles().add(File);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                AppointmentDTO appointmentDTO = appointmentService.saveAppointment(appointment);
                return ResponseEntity.status(HttpStatus.OK).body(appointmentDTO);
            } catch (Exception e) {
                System.out.println("Error create appointment " + e.getMessage());
                MessageResponse message = new MessageResponse(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
    }
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByProfileId(@RequestParam("profileId") String profileId) {
        return ResponseEntity.ok().body(appointmentService.getAppointmentByProfileId(profileId));
    }
    @PatchMapping("/appointments")
    public ResponseEntity<Object> updateAppointment(
            @RequestParam("id") String id,
            @RequestParam("stt") Integer stt,
            @RequestParam("room") String room,
            @RequestParam("profileId") String profileId,
            @RequestParam("doctorId") String doctorId,
            @RequestParam("date") Date date,
            @RequestParam("time") String time,
            @RequestParam(name = "symptom", required = false) String symptom,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "timeSituation", required = false) String timeSituation,
            @RequestParam(name = "files", required = false) MultipartFile[] files,
            @RequestParam(name = "deleteFile", required = false) String fileId
    ) {
            try {
                Appointment appointment = appointmentRepository.findAppointmentById(id);
                Profile profile = profileRepository.findById(profileId);
                Doctor doctor = doctorRepository.findDoctorById(doctorId);
                appointment.setStt(stt);
                appointment.setRoom(room);
                appointment.setDate(date);
                appointment.setTime(time);
                appointment.setProfile(profile);
                appointment.setDoctor(doctor);
                appointment.setSymptom(symptom);
                appointment.setDescription(description);
                appointment.setTimeSituation(timeSituation);
                if (null != files) {
                    Arrays.asList(files).stream().forEach(file -> {
                        try {
                            File File = fileService.store(file);
                            appointment.getFiles().add(File);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                if (null != fileId) {
                    fileService.removeFile(fileId);
                }
                int result = appointmentService.updateAppointment(appointment);
                return ResponseEntity.ok(result);
            } catch (Exception e) {
                System.out.println("Error in update appointment " + e.getMessage());
                MessageResponse message = new MessageResponse(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
    }
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Integer> removeAppointment(@PathVariable String id) {
        int result = appointmentService.removeAppointment(id);
        return ResponseEntity.ok(result);
    }
}
