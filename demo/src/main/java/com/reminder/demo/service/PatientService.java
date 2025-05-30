package com.reminder.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reminder.demo.model.Patient;
import com.reminder.demo.repository.Ipatient;
import com.reminder.demo.DTO.PatientDTO;
import com.reminder.demo.DTO.ResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private Ipatient patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(int patientId) {
        return patientRepository.findById(patientId);
    }

    public ResponseDTO deletePatient(int patientId) {
        try {
            Optional<Patient> patient = getPatientById(patientId);
            if (!patient.isPresent()) {
                return new ResponseDTO("Patient not found", false, null);
            }

            patient.get().setEmail(null);
            patientRepository.save(patient.get());
            return new ResponseDTO("Patient deleted", true, null);
        } catch (Exception e) {
            return new ResponseDTO("Error deleting patient", false, null);
        }
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public ResponseDTO updatePatient(int patientId, PatientDTO patientDTO) {
        try {
            // First find the existing patient
            Optional<Patient> optionalPatient = patientRepository.findById(patientId);
            if (!optionalPatient.isPresent()) {
                return new ResponseDTO("Patient not found", false, null);
            }

            // Get the existing patient and update its fields
            Patient existingPatient = optionalPatient.get();
            existingPatient.setName(patientDTO.getName());
            existingPatient.setEmail(patientDTO.getEmail());
            
            // Save the updated patient entity
            Patient savedPatient = patientRepository.save(existingPatient);
            
            return new ResponseDTO(
                "Patient updated successfully",
                true,
                convertToDTO(savedPatient)
            );
        } catch (Exception e) {
            return new ResponseDTO(
                "Error updating patient: " + e.getMessage(),
                false,
                null
            );
        }
    }

    public PatientDTO convertToDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        return patientDTO;
    }

    public Patient convertToModel(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        return patient;
    }
}