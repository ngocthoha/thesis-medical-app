package com.thesis.medicalapp.models;

public enum SpecialtyType {
    CHUAN_DOAN_HINH_ANH("Chuẩn Đoán Hình Ảnh"),
    CO_XUONG_KHOP("Cơ - Xương - Khớp"),
    NHI_KHOA("Nhi Khoa"),
    SAN_PHU_KHOA("Sản - Phụ Khoa"),
    TAI_MUI_HONG("Tai - Mũi - Họng"),
    TIM_MACH("Tim Mạch"),
    HO_HAP("Hô Hấp và Miễn Dịch Dị Ứng Lâm Sàng"),
    NOI_SOI("Nội Soi"),
    TIEU_HOA("Tiêu Hóa Gan Mật"),
    XET_NGHIEM("Xét Nghiệm"),
    TAM_THAN_KINH("Tâm Thần Kinh"),
    KHOA_NGOAI("Khoa Ngoại")
    ;
    private final String name;

    private SpecialtyType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
