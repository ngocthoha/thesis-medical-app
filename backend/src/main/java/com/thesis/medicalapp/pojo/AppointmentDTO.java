package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Appointment;
import com.thesis.medicalapp.models.File;
import com.thesis.medicalapp.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private String id;
    private Integer stt;
    private Room room;
    private ProfileDTO profile;
    private DoctorDTO doctor;
    private Date date;
    private String time;
    private String symptom;
    private Collection<File> files;
    private String status;
    private Boolean isOnline;

    public static AppointmentDTO from(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .stt(appointment.getStt())
                .room(appointment.getRoom())
                .profile(ProfileDTO.from(appointment.getProfile()))
                .doctor(DoctorDTO.from(appointment.getDoctor()))
                .date(appointment.getDate())
                .time(appointment.getTime())
                .symptom(appointment.getSymptom())
                .files(appointment.getFiles())
                .status(appointment.getStatus())
                .isOnline(appointment.getIsOnline())
                .build();
    }
}
