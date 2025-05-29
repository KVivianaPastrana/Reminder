package com.reminder.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Optional<Patient> patient = getPatientById(patientId);
        if (!patient.isPresent()) {
            return new ResponseDTO(HttpStatus.OK.toString(), "Patient " + patientId + " not found");
        }

        patient.get().setEmail(null);
        patientRepository.save(patient.get());
        return new ResponseDTO(HttpStatus.OK.toString(), "Patient " + patientId + " deleted");
    }

public ResponseDTO save(Patient patient) {
    Patient savedPatient = patientRepository.save(patient);
    System.out.println("ID después de guardar: " + savedPatient.getPatientId()); // Debug
    return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Patient " + savedPatient.getPatientId() + " saved");
}

    public ResponseDTO updatePatient(int patientId, PatientDTO patientDTO) {
        Optional<Patient> patient = getPatientById(patientId);
        if (patient.isPresent()) {
            Patient existingPatient = patient.get();
            existingPatient.setName(patientDTO.getName());
            patientRepository.save(existingPatient);
            return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Patient " + patientId + " updated");

        } else {
            return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Patient " + patientId + " not updated");

        }
    }

    public PatientDTO convertToDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        
        patientDTO.setName(patient.getName());
        return patientDTO;
    }

    public Patient convertToModel(PatientDTO patientDTO) {
    Patient patient = new Patient();
    patient.setName(patientDTO.getName());
    patient.setEmail(patientDTO.getEmail());  // Usa el email del DTO
    return patient;
}

}
