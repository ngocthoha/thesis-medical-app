package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.repository.AppointmentRepository;
import com.thesis.medicalapp.repository.LeaveRequestRepository;
import com.thesis.medicalapp.repository.NotificationRepository;
import com.thesis.medicalapp.repository.ScheduleRepository;
import com.thesis.medicalapp.services.ScheduleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestRepository leaveRequestRepository;
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;
    private final AppointmentRepository appointmentRepository;
    private final NotificationRepository notificationRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("")
    public ResponseEntity<Object> leaveSchedules(@RequestBody LeaveRequest leaveRequestCreate) {
        leaveRequestCreate.setIsApproved(Boolean.FALSE);
        LeaveRequest leaveRequest =  leaveRequestRepository.save(leaveRequestCreate);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(leaveRequest)
        );
    }

    @Async
    public void updateSchedule(LeaveRequest leaveRequest) {
        List<Schedule> schedules = scheduleService.getSchedulesByDateAndDoctor(
                leaveRequest.getDate(),
                leaveRequest.getDoctorId()
        );
        for (Schedule s : schedules) {
            List<String> times = s.getTimes()
                    .stream()
                    .filter(time -> ! leaveRequest.getTimes().contains(time))
                    .collect(Collectors.toList());
            s.setTimes(times);
            scheduleRepository.save(s);
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<Object> approveLeaveSchedules(@RequestParam String leaveId) {
        LeaveRequest leaveRequest =  leaveRequestRepository.findById(leaveId).get();
        leaveRequest.setIsApproved(Boolean.TRUE);
        LeaveRequest leaveRequestResponse =  leaveRequestRepository.save(leaveRequest);
        // update schedule
        updateSchedule(leaveRequestResponse);
        Date dateFormat = new Date();
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(leaveRequestResponse.getDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (String time : leaveRequestResponse.getTimes()) {
            List<Appointment> appointments = appointmentRepository.findAllByDateAndDoctor_IdAndTime(
                    dateFormat, leaveRequestResponse.getDoctorId(), time
            );
            if (!appointments.isEmpty()) {
                for (Appointment appointment : appointments) {
                    appointment.setStatus(Status.CANCEL);
                    User user = appointment.getProfile().getUser();
                    Notification notification = new Notification();
                    notification.setTime(new Date());
                    notification.setTitle("Thông báo hủy lịch hẹn");
                    String text = "";
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateNoti = formatter.format(dateFormat);
                    if (appointment.getCategory().equals(CategoryType.DOCTOR)) {
                        text = "Lịch khám theo yêu cầu với "
                                + appointment.getDoctor().getLevel() + ". " + appointment.getDoctor().getName()
                                + " ngày " + dateNoti
                                + " đã bị hủy do bác sĩ thông báo nghỉ. Bạn vui lòng đặt lại lịch hẹn.";
                    }
                    notification.setText(text);
                    notification.setObjectId(appointment.getId());
                    notification.setToUser(user.getUsername());
                    notification.setType(NotificationType.CANCEL_APPOINTMENT);
                    Notification notificationRes = notificationRepository.save(notification);
                    simpMessagingTemplate.convertAndSendToUser(notification.getToUser(),"/queue/notification", notificationRes);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(leaveRequestResponse)
        );
    }

    @GetMapping("")
    public ResponseEntity<Object> getLeaves(
            @RequestParam Integer size,
            @RequestParam Integer page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LeaveRequest> leaveRequests =  leaveRequestRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(leaveRequests.getContent(), leaveRequests)
        );
    }
}
