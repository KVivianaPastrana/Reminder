package com.reminder.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.reminder.demo.model.Medicin;
import com.reminder.demo.model.Patient;
import com.reminder.demo.model.Prescription;
import com.reminder.demo.repository.Iprescription;
import com.reminder.demo.DTO.PrescriptionDTO;
import com.reminder.demo.DTO.ResponseDTO;
import com.reminder.demo.repository.Imedicine;
import com.reminder.demo.repository.Ipatient;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;


@Service
public class PrescriptionService {

    @Autowired
    private Iprescription prescriptionRepository;
    @Autowired
private Ipatient patientRepository;

@Autowired
private Imedicine medicineRepository;

public List<PrescriptionDTO> getAllPrescriptionDTOs() {
    return prescriptionRepository.findAll()
        .stream()
        .map(this::convertToDTO)
        .toList();
}

    public Optional<Prescription> getPrescriptionById(int prescriptionId) {
        return prescriptionRepository.findById(prescriptionId);
    }

    public ResponseDTO deletePrescription(int prescriptionId) {
    try{
    Optional<Prescription> prescription = getPrescriptionById(prescriptionId);
    if (!prescription.isPresent()) {
        return new ResponseDTO( "Prescription not found", false, null);
    }

    prescription.get().setDose(null);
    prescriptionRepository.save(prescription.get());
    return new ResponseDTO("Prescription deleted", true, null);
    } catch (Exception e) {
        return new ResponseDTO("Error deleting prescription", false, null);
    }

    }
    

public ResponseDTO save(PrescriptionDTO dto) {
    try {
        Prescription prescription = convertToModel(dto);

        // Asignar el paciente
        Optional<Patient> patientOpt = patientRepository.findById(dto.getPatientId());
        if (!patientOpt.isPresent()) {
            return new ResponseDTO("Patient not found", false, null);
        }
        prescription.setPatient(patientOpt.get());

        // Asignar la medicina
        Optional<Medicin> medicineOpt = medicineRepository.findById(dto.getMedicineId());
        if (!medicineOpt.isPresent()) {
            return new ResponseDTO("Medicine not found", false, null);
        }
        prescription.setMedicine(medicineOpt.get());

        Prescription saved = prescriptionRepository.save(prescription);
        return new ResponseDTO("Prescription saved successfully", true, convertToDTO(saved));

    } catch (Exception e) {
        return new ResponseDTO("Error saving prescription: " + e.getMessage(), false, null);
    }

    }
    public ResponseDTO updatePrescription(int prescriptionId, PrescriptionDTO dto) {
    try {
        Optional<Prescription> op = prescriptionRepository.findById(prescriptionId);
        if (!op.isPresent()) {
            return new ResponseDTO("Prescription not found", false, null);
        }

        Prescription prescription = op.get();

        // Ejemplos de campos que puedes actualizar
        if (dto.getDose() != null)      prescription.setDose(dto.getDose());
        if (dto.getSchedule() != null)  prescription.setSchedule(dto.getSchedule());

        // → guarda
        prescriptionRepository.save(prescription);

        return new ResponseDTO("Prescription updated", true, convertToDTO(prescription));

    } catch (Exception e) {
        return new ResponseDTO("Error updating prescription: " + e.getMessage(), false, null);
    }
}
public PrescriptionDTO convertToDTO(Prescription p) {
    PrescriptionDTO dto = new PrescriptionDTO();

    dto.setPrescriptionId(p.getPrescriptionId());

    if (p.getPatient() != null) {
        dto.setPatientId(p.getPatient().getPatientId());
        dto.setPatientName(p.getPatient().getName());
    } else {
        dto.setPatientId(0);
        dto.setPatientName("N/A");
    }

    if (p.getMedicine() != null) {
        dto.setMedicineId(p.getMedicine().getMedicineId());
        dto.setMedicineName(p.getMedicine().getMedicineName());
    } else {
        dto.setMedicineId(0);
        dto.setMedicineName("N/A");
    }

    dto.setSchedule(p.getSchedule());
    dto.setDose(p.getDose());

    return dto;
}



    public Prescription convertToModel(PrescriptionDTO prescriptionDTO) {
    Prescription prescription = new Prescription();
    prescription.setDose(prescriptionDTO.getDose());
    prescription.setSchedule(prescriptionDTO.getSchedule());  // Usa el schedule del DTO
    return prescription;
}


}
