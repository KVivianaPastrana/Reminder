package com.reminder.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
  
import com.reminder.demo.service.PatientService;
import com.reminder.demo.model.Patient;
import com.reminder.demo.DTO.PatientDTO;
import com.reminder.demo.DTO.ResponseDTO;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Object> getPatientById(@PathVariable int patientId) {
        var patient = patientService.getPatientById(patientId);
        if (patient.isPresent()) {
            return new ResponseEntity<>(patientService.convertToDTO(patient.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPatient(@RequestBody PatientDTO patientDTO) {
        ResponseDTO respuesta = patientService.save(patientService.convertToModel(patientDTO));
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<Object> updatePatient(@PathVariable int patientId, @RequestBody PatientDTO patientDTO) {
        ResponseDTO respuesta = patientService.updatePatient(patientId, patientDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Object> deletePatient(@PathVariable int patientId) {
        ResponseDTO respuesta = patientService.deletePatient(patientId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
