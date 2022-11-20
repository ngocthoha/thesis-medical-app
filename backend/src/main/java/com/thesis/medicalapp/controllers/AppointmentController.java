package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.exception.ApiRequestException;
import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.payload.AppointmentRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.payload.response.MessageResponse;
import com.thesis.medicalapp.pojo.AppointmentDTO;
import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.repository.*;
import com.thesis.medicalapp.services.*;
import com.thesis.medicalapp.utils.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final ProfileService profileService;
    private final DoctorService doctorService;
    private final RoomRepository roomRepository;
    private final MedicalFileRepository medicalFileRepository;
    private final ServiceRepository serviceRepository;
    private final AppointmentRepository appointmentRepository;

    @PostMapping("/appointments")
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        Profile profile = profileService.findProfileById(appointmentRequest.getProfileId());
        if (profile == null) throw new ApiRequestException("Không tìm thấy hồ sơ!");
        appointment.setProfile(profile);
        if (appointmentRequest.getDoctorId()  == null && appointmentRequest.getServiceId() == null)
            throw new ApiRequestException("doctor or service must not be blank");
        if (appointmentRequest.getDoctorId() != null) {
            System.out.println("create doctor appointment");
            Doctor doctor = doctorService.findDoctorById(appointmentRequest.getDoctorId());
            if (doctor == null) throw new ApiRequestException("Không tìm thấy bác sĩ!");
            appointment.setDoctor(doctor);
        } else {
           Optional<HospitalService> service = serviceRepository.findById(appointmentRequest.getServiceId());
           if (!service.isPresent()) throw new ApiRequestException("Không tìm thấy dịch vụ!");
           appointment.setService(service.get());
        }
        Date dateFormat = new Date();
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(appointmentRequest.getDate());
        } catch (Exception e) {
            throw new ApiRequestException("Invalid date!");
        }
        appointment.setDate(dateFormat);
        Room room = roomRepository.findRoomById(appointmentRequest.getRoomId());
        if (room == null) throw new ApiRequestException("Không tìm thấy phòng!");
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
        appointment.setStatus(Status.PENDING);
        appointment.setType(appointmentRequest.getType());
        appointment.setCategory(appointmentRequest.getCategory());
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        Long code = sequenceGenerator.nextId();
        appointment.setCode(code);
        appointment.setFee(appointmentRequest.getFee());
        appointment.setIsPaid(appointmentRequest.getIsPaid());
        appointment.setOrderId(appointmentRequest.getOrderId());
        String dataQrCode =
                appointment.getProfile().getFirstName() + appointment.getProfile().getLastName() + "\n"
                + appointment.getTime() + "\n"
                + appointment.getDoctor().getName() + "\n"
                + appointment.getId() + "\n"
                + appointment.getOrderId();
        String qrcode = QRCode.createQR(dataQrCode, 300, 300);
        appointment.setQrcode(qrcode);
        appointment.setPaymentType(appointmentRequest.getPaymentType());
        AppointmentDTO appointmentDTO = appointmentService.saveAppointment(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(appointmentDTO)
        );
    }

    @GetMapping("/appointments")
    public ResponseEntity<Object> getAppointmentByProfileId(@RequestParam("profileId") String profileId) {
        List<AppointmentDTO> appointmentDTOS = appointmentService.getAppointmentByProfileId(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(appointmentDTOS)
        );
    }

    @GetMapping("/appointments/user")
    public ResponseEntity<Object> getAppointmentByUser(
            @RequestParam Integer size,
            @RequestParam Integer page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AppointmentDTO> pageAppointment = appointmentService.getAppointmentByUser(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(pageAppointment.getContent(), pageAppointment)
        );
    }

    @PatchMapping("/appointments")
    public ResponseEntity<Object> partialUpdateAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentService.findAppointmentById(appointmentRequest.getId());
        if (appointment == null) {
            if (appointmentRequest.getOrderId() != null) {
                appointment = appointmentRepository.findByOrderId(appointmentRequest.getOrderId());
            } else throw new ApiRequestException("Không tìm thấy lịch hẹn!");
        }
        if (appointmentRequest.getProfileId() != null) {
            Profile profile = profileService.findProfileById(appointmentRequest.getProfileId());
            if (profile == null) throw new ApiRequestException("Không tìm thấy hồ sơ khám!");
            appointment.setProfile(profile);
        }
        if (appointmentRequest.getDoctorId() != null) {
            Doctor doctor = doctorService.findDoctorById(appointmentRequest.getDoctorId());
            if (doctor == null) throw new ApiRequestException("Không tìm thấy bác sĩ!");
            appointment.setDoctor(doctor);
        }
        if (appointmentRequest.getRoomId() != null) {
            Room room = roomRepository.findRoomById(appointmentRequest.getRoomId());
            if (room == null) throw new ApiRequestException("Không tìm thấy phòng!");
            appointment.setRoom(room);
        }
        if (appointmentRequest.getDate() != null) {
            Date dateFormat = new Date();
            try {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(appointmentRequest.getDate());
            } catch (Exception e) {
                throw new ApiRequestException("Invalid date!");
            }
            appointment.setDate(dateFormat);
        }
        if (appointmentRequest.getTime() != null)  appointment.setTime(appointmentRequest.getTime());
        if (appointmentRequest.getSymptom() != null)  appointment.setSymptom(appointmentRequest.getSymptom());
        if (appointmentRequest.getFee() != null)  appointment.setFee(appointmentRequest.getFee());
        if (appointmentRequest.getIsPaid() != null)  appointment.setIsPaid(appointmentRequest.getIsPaid());
        appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok(
                new ApiResponse(null)
        );
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<ApiResponse> removeAppointment(@PathVariable String id) {
        int result = appointmentService.removeAppointment(id);
        return ResponseEntity.ok(
                new ApiResponse(null)
        );
    }

    @GetMapping("/appointments/doctor")
    public ResponseEntity<ApiResponse> getAppointmentsByDateAndDoctor(@RequestParam String date) {
        List<AppointmentDTO> list = appointmentService.getAppointmentsByDateAndDoctor(date);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(list)
        );
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
                new ApiResponse(appointment)
        );
    }
}
