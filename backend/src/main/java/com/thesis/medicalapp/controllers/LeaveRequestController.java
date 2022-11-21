package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Hospital;
import com.thesis.medicalapp.models.LeaveRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.HospitalDTO;
import com.thesis.medicalapp.repository.LeaveRequestRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestRepository leaveRequestRepository;

    @PostMapping("")
    public ResponseEntity<Object> leaveSchedules(@RequestBody LeaveRequest leaveRequestCreate) {
        leaveRequestCreate.setIsApproved(Boolean.FALSE);
        LeaveRequest leaveRequest =  leaveRequestRepository.save(leaveRequestCreate);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(leaveRequest)
        );
    }

    @PostMapping("/approve")
    public ResponseEntity<Object> approveLeaveSchedules(@RequestParam String leaveId) {
        LeaveRequest leaveRequest =  leaveRequestRepository.findById(leaveId).get();
        leaveRequest.setIsApproved(Boolean.TRUE);
        LeaveRequest leaveRequestResponse =  leaveRequestRepository.save(leaveRequest);
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
