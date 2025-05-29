package com.reminder.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
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
public Prescription createPrescription(@RequestBody Prescription prescription) {
    // Verifica que patient y medicine no sean null
    if (prescription.getPatient() == null || prescription.getMedicine() == null) {
        throw new RuntimeException("Patient y Medicine son obligatorios");
    }
    
    // Opcional: Verifica si existen en la base de datos
    Patient patient = patientRepository.findById(prescription.getPatient().getPatientId())
        .orElseThrow(() -> new RuntimeException("Patient no encontrado"));
    
    Medicin medicine = medicineRepository.findById(prescription.getMedicine().getMedicineId())
        .orElseThrow(() -> new RuntimeException("Medicine no encontrado"));
    
    prescription.setPatient(patient);
    prescription.setMedicine(medicine);
    
    return prescriptionRepository.save(prescription);
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
