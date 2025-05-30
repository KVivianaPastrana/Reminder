package com.reminder.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

import com.reminder.demo.service.PrescriptionService;
import com.reminder.demo.model.Medicin;
import com.reminder.demo.model.Patient;
import com.reminder.demo.model.Prescription;
import com.reminder.demo.DTO.PrescriptionDTO;
import com.reminder.demo.DTO.ResponseDTO;
import com.reminder.demo.repository.Imedicine;
import com.reminder.demo.repository.Ipatient;
import com.reminder.demo.repository.Iprescription;
@RestController
@RequestMapping("/prescription")

public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private Ipatient patientRepository;
    @Autowired
    private Imedicine medicineRepository;
    @Autowired
    private Iprescription prescriptionRepository;
@GetMapping
public ResponseEntity<List<PrescriptionDTO>> list() {
    return ResponseEntity.ok(prescriptionService.getAllPrescriptionDTOs());
}


    @GetMapping("/{prescriptionId}")
    public ResponseEntity<Object> getPrescriptionById(@PathVariable int prescriptionId) {
        var prescription = prescriptionService.getPrescriptionById(prescriptionId);
        if (prescription.isPresent()) {
            return new ResponseEntity<>(prescriptionService.convertToDTO(prescription.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

@PostMapping
public ResponseEntity<?> createPrescription(@RequestBody PrescriptionDTO dto) {

    // 1. Validaciones de existencia
    Patient patient = patientRepository.findById(dto.getPatientId())
            .orElse(null);
    if (patient == null)
        return ResponseEntity.badRequest().body("Paciente no existe");

    Medicin medicine = medicineRepository.findById(dto.getMedicineId())
            .orElse(null);
    if (medicine == null)
        return ResponseEntity.badRequest().body("Medicamento no existe");

    // 2. Construir entidad
    Prescription p = new Prescription();
    p.setPatient(patient);
    p.setMedicine(medicine);
    p.setSchedule(dto.getSchedule());
// HH:mm
    p.setDose(dto.getDose());

    Prescription saved = prescriptionRepository.save(p);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(prescriptionService.convertToDTO(saved));
}

    @PutMapping("/{prescriptionId}")
    public ResponseEntity<Object> updatePrescription(@PathVariable int prescriptionId, @RequestBody PrescriptionDTO prescriptionDTO) {
        ResponseDTO respuesta = prescriptionService.updatePrescription(prescriptionId, prescriptionDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<Object> deletePrescription(@PathVariable int prescriptionId) {
        ResponseDTO respuesta = prescriptionService.deletePrescription(prescriptionId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
