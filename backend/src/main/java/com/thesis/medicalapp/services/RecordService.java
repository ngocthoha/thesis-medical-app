package com.thesis.medicalapp.services;

import com.thesis.medicalapp.models.Record;
import com.thesis.medicalapp.pojo.RecordDTO;

import java.util.List;

public interface RecordService {
    RecordDTO saveRecord(Record record);
    List<RecordDTO> getRecords();
    List<RecordDTO> getRecordsByProfile(String profileId);
}
