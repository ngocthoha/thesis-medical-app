package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Record;
import com.thesis.medicalapp.pojo.ProfileDTO;
import com.thesis.medicalapp.pojo.RecordDTO;
import com.thesis.medicalapp.repository.RecordRepository;
import com.thesis.medicalapp.services.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    @Override
    public List<RecordDTO> getRecords() {
        List<Record> records = recordRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        List<RecordDTO> recordDTOS = records.stream().map(p -> {
            RecordDTO recordDTO = RecordDTO.from(p);
            return recordDTO;
        }).collect(Collectors.toList());
        return recordDTOS;
    }

    @Override
    public RecordDTO saveRecord(Record record) {
        Record recordEntity = recordRepository.save(record);
        RecordDTO recordResponse = RecordDTO.from(recordEntity);
        return recordResponse;
    }

    @Override
    public List<RecordDTO> getRecordsByProfile(String profileId) {
        List<Record> records = recordRepository.findByAppointment_Profile_Id(profileId)
                .stream()
                .collect(Collectors.toList());
        List<RecordDTO> recordDTOS = records.stream().map(p -> {
            RecordDTO recordDTO = RecordDTO.from(p);
            return recordDTO;
        }).collect(Collectors.toList());
        return recordDTOS;
    }
}
