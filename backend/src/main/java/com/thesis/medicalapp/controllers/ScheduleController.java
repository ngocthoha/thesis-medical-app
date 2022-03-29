package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.services.ScheduleService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ScheduleDTO> saveSchedule(@RequestBody ScheduleDTO schedule) {
        return ResponseEntity.ok().body(scheduleService.saveSchedule(schedule));
    }
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleDTO>> getSchedules() {
        return ResponseEntity.ok().body(scheduleService.getSchedules());
    }
    @GetMapping("/schedules/doctors")
    public ResponseEntity<List<ScheduleDTO>> getDoctorsBySchedule(@RequestParam("specialty") String specialty, @RequestParam("date") String date) {
        return ResponseEntity.ok().body(scheduleService.getDoctorsBySchedule(specialty, date));
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
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByDoctor() {
        return ResponseEntity.ok().body(scheduleService.getSchedulesByDoctor());
    }
}
