package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.Record;
import com.thesis.medicalapp.pojo.RecordDTO;
import com.thesis.medicalapp.repository.RecordRepository;
import com.thesis.medicalapp.services.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    @Override
    public List<RecordDTO> getRecords() {
        List<RecordDTO> recordDTOS = new ArrayList<>();
        return recordDTOS;
    }

    @Override
    public RecordDTO saveRecord(Record record) {
        Record recordEntity = recordRepository.save(record);
        RecordDTO recordResponse = RecordDTO.from(recordEntity);
        return recordResponse;
    }
}
