package com.thesis.medicalapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SpecialtyController {
    private String[] specialty = {
            "CHUẨN ĐOÁN HÌNH ẢNH",
            "CƠ - XƯƠNG - KHỚP",
            "NHI KHOA",
            "SẢN - PHỤ KHOA",
            "TAI - MŨI - HỌNG",
            "TIM MẠCH",
            "HÔ HẤP VÀ MIỄN DỊCH DỊ ỨNG LÂM SÀNG",
            "NỘI SOI",
            "TIÊU HÓA GAN MẬT",
            "XÉT NGHIỆM"
    };
    @GetMapping("/specialties")
    public String[] getSpecialty() {
        return this.specialty;
    }
}
