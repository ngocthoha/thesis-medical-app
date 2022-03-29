package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Integer id;
    private Date date;
    private String room;
    private List<String> times;
    private DoctorDTO doctor;

    public static ScheduleDTO from(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .date(schedule.getDate())
                .room(schedule.getRoom())
                .times(schedule.getTimes())
                .doctor(DoctorDTO.from(schedule.getDoctor()))
                .build();
    }
}
