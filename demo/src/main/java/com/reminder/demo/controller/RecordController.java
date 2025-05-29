package com.reminder.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.reminder.demo.service.RecordService;
import com.reminder.demo.model.Record;
import com.reminder.demo.DTO.RecordDTO; 
import com.reminder.demo.DTO.ResponseDTO;
import com.reminder.demo.repository.Irecord;
import com.reminder.demo.repository.Iprescription;
@RestController
@RequestMapping("/record")

public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<Object> getRecordById(@PathVariable int recordId) {
        var record = recordService.getRecordById(recordId);
        if (record.isPresent()) {
            return new ResponseEntity<>(recordService.convertToDTO(record.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@PostMapping
public ResponseEntity<Object> createRecord(@RequestBody RecordDTO recordDTO) {
    ResponseDTO respuesta = recordService.save(recordDTO);
    return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
}

    @PutMapping("/{recordId}")
    public ResponseEntity<Object> updateRecord(@PathVariable int recordId, @RequestBody RecordDTO recordDTO) {
        ResponseDTO respuesta = recordService.updateRecord(recordId, recordDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Object> deleteRecord(@PathVariable int recordId) {
        ResponseDTO respuesta = recordService.deleteRecord(recordId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
