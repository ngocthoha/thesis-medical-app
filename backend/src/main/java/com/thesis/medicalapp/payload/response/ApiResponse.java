package com.thesis.medicalapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T results;

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.results = null;
    }
}
