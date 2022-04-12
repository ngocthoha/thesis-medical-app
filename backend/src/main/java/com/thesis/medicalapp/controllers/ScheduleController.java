package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Room;
import com.thesis.medicalapp.payload.ListSchedule;
import com.thesis.medicalapp.payload.RoomDate;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.repository.RoomRepository;
import com.thesis.medicalapp.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final RoomRepository roomRepository;
    @PostMapping("/schedules")
    public ResponseEntity<ApiResponse> saveSchedule(@RequestBody ListSchedule request) {
        try {
            List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
            for (RoomDate d : request.getList()) {
                ScheduleDTO schedule = new ScheduleDTO();
                schedule.setDate(d.getDate());
                schedule.setDoctor(request.getDoctor());
                Room room = roomRepository.findRoomById(d.getRoom().getId());
                if (room == null) {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ApiResponse<>(0, "Can't find room", null)
                    );
                }
                schedule.setRoom(room);
                schedule.setTimes(d.getTimes());
                ScheduleDTO scheduleDTO = scheduleService.saveSchedule(schedule);
                scheduleDTOS.add(scheduleDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", scheduleDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleDTO>> getSchedules() {
        return ResponseEntity.ok().body(scheduleService.getSchedules());
    }
    @GetMapping("/schedules/doctors")
    public ResponseEntity<ApiResponse> getDoctorsBySchedule(@RequestParam("specialty") String specialty, @RequestParam("date") String date) {
        try {
            List<ScheduleDTO> scheduleDTOS = scheduleService.getDoctorsBySchedule(specialty, date);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", scheduleDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @PatchMapping("/schedules")
    public ResponseEntity<Integer> updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        int result = scheduleService.updateSchedule(scheduleDTO);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Integer> removeSchedule(@PathVariable String id) {
        int result = scheduleService.removeSchedule(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/doctor/schedules")
    public ResponseEntity<ApiResponse> getSchedulesByDoctor() {
        try {
            List<ScheduleDTO> scheduleDTOS = scheduleService.getSchedulesByDoctor();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", scheduleDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
}
