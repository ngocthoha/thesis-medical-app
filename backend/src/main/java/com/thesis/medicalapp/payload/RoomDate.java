package com.thesis.medicalapp.payload;

import com.thesis.medicalapp.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDate {
    private Date date;
    private List<String> times;
    private Room room;
}
