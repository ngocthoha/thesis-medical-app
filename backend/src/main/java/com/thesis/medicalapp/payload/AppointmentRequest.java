package com.thesis.medicalapp.payload;

import com.thesis.medicalapp.models.MedicalFile;
import com.thesis.medicalapp.models.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    @NotEmpty
    @NotBlank
    private String profileId;
    @NotEmpty
    @NotBlank
    private String doctorId;
    @NotNull
    @NotBlank
    private String date;
    @NotEmpty
    @NotBlank
    private String roomId;
    private String symptom;
    @NotNull
    @NotBlank
    private String time;
    @NotNull
    private ScheduleType type;
    private List<MedicalFile> files;
}