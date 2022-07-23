package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.payload.response.MessageResponse;
import com.thesis.medicalapp.pojo.AppointmentDTO;
import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.repository.DoctorRepository;
import com.thesis.medicalapp.repository.ProfileRepository;
import com.thesis.medicalapp.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@CrossOrigin("*")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final ProfileService profileService;
    private final DoctorService doctorService;
    private final ScheduleService scheduleService;
    private final FileService fileService;

    @PostMapping("/appointments")
    public ResponseEntity<ApiResponse> saveAppointment(
            @RequestParam("profileId") String profileId,
            @RequestParam("doctorId") String doctorId,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam(name = "symptom", required = false) String symptom,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "timeSituation", required = false) String timeSituation,
            @RequestParam(name = "selfTreatment", required = false) Boolean selfTreatment,
            @RequestParam(name = "files", required = false) MultipartFile[] files
    ) {
            try {
                Appointment appointment = new Appointment();
                Profile profile = profileService.findProfileById(profileId);
                Doctor doctor = doctorService.findDoctorById(doctorId);
                appointment.setProfile(profile);
                appointment.setDoctor(doctor);
                Date dateFormat = new Date();
                try {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                appointment.setDate(dateFormat);
                Integer sttMax = appointmentService.max(dateFormat);
                if (sttMax == null) {sttMax = 0;}
                appointment.setStt(sttMax + 1);
                ScheduleDTO scheduleDTO = scheduleService.getScheduleByDateAndDoctor(dateFormat, doctor);
                appointment.setRoom(scheduleDTO.getRoom());
                appointment.setTime(time);
                appointment.setSymptom(symptom);
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
                appointment.setStatus("ACTIVE");
                AppointmentDTO appointmentDTO = appointmentService.saveAppointment(appointment);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResponse(1, "Success", appointmentDTO)
                );
            } catch (Exception e) {
                System.out.println("Error create appointment " + e.getMessage());
                MessageResponse message = new MessageResponse(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ApiResponse(0, e.getMessage(), null)
                );
            }
    }
    @GetMapping("/appointments")
    public ResponseEntity<ApiResponse> getAppointmentByProfileId(@RequestParam("profileId") String profileId) {
        try {
            List<AppointmentDTO> appointmentDTOS = appointmentService.getAppointmentByProfileId(profileId);
            System.out.println("get appointmet by profile");
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", appointmentDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @PatchMapping("/appointments")
    public ResponseEntity<ApiResponse> updateAppointment(
            @RequestParam("id") String id,
            @RequestParam("stt") Integer stt,
            @RequestParam(name = "room", required = false) Room room,
            @RequestParam("profileId") String profileId,
            @RequestParam("doctorId") String doctorId,
            @RequestParam("date") Date date,
            @RequestParam("time") String time,
            @RequestParam(name = "symptom", required = false) String symptom,
            @RequestParam(name = "files", required = false) MultipartFile[] files,
            @RequestParam(name = "deleteFile", required = false) String fileId,
            @RequestParam(name = "status", required = false) String status
    ) {
            try {
                Appointment appointment = appointmentService.findAppointmentById(id);
                Profile profile = profileService.findProfileById(profileId);
                Doctor doctor = doctorService.findDoctorById(doctorId);
                appointment.setStt(stt);
                appointment.setRoom(room);
                appointment.setDate(date);
                appointment.setTime(time);
                appointment.setProfile(profile);
                appointment.setDoctor(doctor);
                appointment.setSymptom(symptom);
                appointment.setStatus(status);
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
                appointmentService.updateAppointment(appointment);
                return ResponseEntity.ok(
                        new ApiResponse(1, "Success", null)
                );
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ApiResponse(0, e.getMessage(), null)
                );
            }
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
