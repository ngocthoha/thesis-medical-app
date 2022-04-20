package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Room;
import com.thesis.medicalapp.repository.RoomRepository;
import com.thesis.medicalapp.services.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
}
