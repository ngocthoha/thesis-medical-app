package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.ProfileDTO;
import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    @PostMapping("/schedules")
    public ResponseEntity<ApiResponse> saveSchedule(@RequestBody ScheduleDTO schedule) {
        try {
            ScheduleDTO scheduleDTO = scheduleService.saveSchedule(schedule);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", scheduleDTO)
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
    public ResponseEntity<Integer> removeSchedule(@PathVariable Integer id) {
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
