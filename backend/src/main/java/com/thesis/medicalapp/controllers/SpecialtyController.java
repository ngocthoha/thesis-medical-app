package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SpecialtyController {
    private String[] specialties = {
            "Chuẩn Đoán Hình Ảnh",
            "Cơ - Xương - Khớp",
            "Nhi Khoa",
            "Sản - Phụ Khoa",
            "Tai - Mũi - Họng",
            "Tim Mạch",
            "Hô Hấp và Miễn Dịch Dị Ứng Lâm Sàng",
            "Nội Soi",
            "Tiêu Hóa Gan Mật",
            "Xét Nghiệm",
            "Tâm Thần Kinh",
            "Khoa Ngoại"
    };
    @GetMapping("/specialties")
    public ResponseEntity<ApiResponse> getSpecialty() {
        return ResponseEntity.ok(
                new ApiResponse(this.specialties)
        );
    }
}
