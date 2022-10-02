package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private String id;
    private Long code;
    @NotNull
    private Room room;
    @NotNull
    private ProfileDTO profile;
    @NotNull
    private DoctorDTO doctor;
    @NotNull
    private Date date;
    @NotEmpty
    @NotBlank
    private String time;
    @NotEmpty
    @NotBlank
    private String symptom;
    private Collection<MedicalFile> files;
    private Status status;
    @NotNull
    private ScheduleType type;

    public static AppointmentDTO from(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .code(appointment.getCode())
                .room(appointment.getRoom())
                .profile(ProfileDTO.from(appointment.getProfile()))
                .doctor(DoctorDTO.from(appointment.getDoctor()))
                .date(appointment.getDate())
                .time(appointment.getTime())
                .symptom(appointment.getSymptom())
                .files(appointment.getFiles())
                .status(appointment.getStatus())
                .type(appointment.getType())
                .build();
    }
}
