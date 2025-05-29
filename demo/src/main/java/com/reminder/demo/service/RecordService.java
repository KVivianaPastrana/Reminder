package com.reminder.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.reminder.demo.model.Record;
import com.reminder.demo.repository.Irecord;
import com.reminder.demo.DTO.RecordDTO;
import com.reminder.demo.DTO.ResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

@Autowired
private Irecord recordRepository;

public List<Record> getAllRecords() {
    return recordRepository.findAll();
}

public Optional<Record> getRecordById(int recordId) {
    return recordRepository.findById(recordId);
}

public ResponseDTO deleteRecord(int recordId) {
    Optional<Record> record = getRecordById(recordId);
    if (!record.isPresent()) {
        return new ResponseDTO(HttpStatus.OK.toString(), "Record " + recordId + " not found");
    }

    record.get().setEventType(null);
    recordRepository.save(record.get());
    return new ResponseDTO(HttpStatus.OK.toString(), "Record " + recordId + " deleted");
}

public ResponseDTO save(RecordDTO recordDTO) {
    // Convertir DTO a entidad
    Record record = convertToModel(recordDTO);
    
    // Guardar la entidad
    Record savedRecord = recordRepository.save(record);
    
    // Retornar respuesta usando el constructor correcto
    return new ResponseDTO(
        "Record " + savedRecord.getRecordId() + " created",
        true, // success
        convertToDTO(savedRecord));
}

public ResponseDTO updateRecord(int recordId, RecordDTO recordDTO) {
    Optional<Record> record = getRecordById(recordId);
    if (record.isPresent()) {
        Record existingRecord = record.get();
        existingRecord.setEventType(recordDTO.getEventType());
        recordRepository.save(existingRecord);
        return new ResponseDTO(
            HttpStatus.OK.toString(),
            "Record " + recordId + " updated");

    } else {
        return new ResponseDTO(
            HttpStatus.OK.toString(),
            "Record " + recordId + " not updated");

    }
}

    public RecordDTO convertToDTO(Record record) {
        RecordDTO recordDTO = new RecordDTO();
        
        recordDTO.setEventType(record.getEventType());
        return recordDTO;
    }

    public Record convertToModel(RecordDTO recordDTO) {
    Record record = new Record();
    record.setEventType(recordDTO.getEventType());
    record.setEventDate(recordDTO.getEventDate());  // Usa el eventDate del DTO
    return record;
}

}
