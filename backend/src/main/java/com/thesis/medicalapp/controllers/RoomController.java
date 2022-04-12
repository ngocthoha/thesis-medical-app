package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.Room;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<ApiResponse> getRooms() {
        try {
            List<Room> rooms = roomService.getRooms();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", rooms)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @PostMapping("/rooms")
    public ResponseEntity<ApiResponse> saveRoom(@RequestBody Room room) {
        try {
            Room roomEntity = roomService.saveRoom(room);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", roomEntity)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
}
