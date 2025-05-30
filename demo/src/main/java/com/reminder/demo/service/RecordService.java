package com.reminder.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reminder.demo.model.Prescription;
import com.reminder.demo.model.Record;
import com.reminder.demo.repository.Iprescription;
import com.reminder.demo.repository.Irecord;
import com.reminder.demo.DTO.RecordDTO;
import com.reminder.demo.DTO.ResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private final Irecord recordRepository;
    private final Iprescription prescriptionRepository;

    @Autowired
    public RecordService(Irecord recordRepository, Iprescription prescriptionRepository) {
        this.recordRepository = recordRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    // Get all records
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Optional<Record> getRecordById(int recordId) {
        return recordRepository.findById(recordId);
    }

    public ResponseDTO deleteRecord(int recordId) {
        try {
            return recordRepository.findById(recordId)
                .map(record -> {
                    record.setEventType(null); // Soft delete
                    recordRepository.save(record);
                    return new ResponseDTO("Record deleted successfully", true, null);
                })
                .orElse(new ResponseDTO("Record not found with id: " + recordId, false, null));
        } catch (Exception e) {
            return new ResponseDTO("Error deleting record: " + e.getMessage(), false, null);
        }
    }

    public ResponseDTO save(RecordDTO recordDTO) {
        try {
            // Validación de entrada
            if (recordDTO.getPrescriptionId() == null) {
                return new ResponseDTO("Prescription ID is required", false, null);
            }

            Record record = convertToModel(recordDTO);
            Record savedRecord = recordRepository.save(record);
            
            return new ResponseDTO(
                "Record created successfully", 
                true, 
                convertToDTO(savedRecord)
            );
        } catch (IllegalArgumentException e) {
            return new ResponseDTO(e.getMessage(), false, null);
        } catch (Exception e) {
            return new ResponseDTO(
                "Error creating record: " + e.getMessage(),
                false,
                null
            );
        }
    }

    public ResponseDTO updateRecord(int recordId, RecordDTO recordDTO) {
        try {
            return recordRepository.findById(recordId)
                .map(existingRecord -> {
                    updateRecordFromDTO(existingRecord, recordDTO);
                    Record updatedRecord = recordRepository.save(existingRecord);
                    return new ResponseDTO(
                        "Record updated successfully",
                        true,
                        convertToDTO(updatedRecord)
                    );
                })
                .orElse(new ResponseDTO(
                    "Record not found with id: " + recordId,
                    false,
                    null
                ));
        } catch (IllegalArgumentException e) {
            return new ResponseDTO(e.getMessage(), false, null);
        } catch (Exception e) {
            return new ResponseDTO(
                "Error updating record: " + e.getMessage(),
                false,
                null
            );
        }
    }

    private void updateRecordFromDTO(Record record, RecordDTO dto) {
        record.setEventType(dto.getEventType());
        record.setEventDate(new java.sql.Date(dto.getEventDate().getTime()));
        record.setEventTime(dto.getEventTime());
        
        if (dto.getPrescriptionId() != null) {
            Prescription prescription = prescriptionRepository.findById(dto.getPrescriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found with id: " + dto.getPrescriptionId()));
            record.setPrescription(prescription);
        }
    }

    public RecordDTO convertToDTO(Record record) {
        RecordDTO dto = new RecordDTO();
        dto.setRecordId(record.getRecordId());
        dto.setEventType(record.getEventType());
        dto.setEventDate(new java.util.Date(record.getEventDate().getTime()));
        dto.setEventTime(record.getEventTime());
        
        if (record.getPrescription() != null) {
            dto.setPrescriptionId(record.getPrescription().getPrescriptionId());
        }
        
        return dto;
    }

    public Record convertToModel(RecordDTO recordDTO) {
        Record record = new Record();
        record.setEventType(recordDTO.getEventType());
        record.setEventDate(new java.sql.Date(recordDTO.getEventDate().getTime()));
        record.setEventTime(recordDTO.getEventTime());
        
        if (recordDTO.getPrescriptionId() == null) {
            throw new IllegalArgumentException("Prescription ID is required");
        }
        
        Prescription prescription = prescriptionRepository.findById(recordDTO.getPrescriptionId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Prescription not found with id: " + recordDTO.getPrescriptionId()));
        
        record.setPrescription(prescription);
        return record;
    }
}