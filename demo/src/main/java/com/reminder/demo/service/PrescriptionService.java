package com.reminder.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.reminder.demo.model.Prescription;
import com.reminder.demo.repository.Iprescription;
import com.reminder.demo.DTO.PrescriptionDTO;
import com.reminder.demo.DTO.ResponseDTO;


import java.util.List;
import java.util.Optional;


@Service
public class PrescriptionService {

    @Autowired
    private Iprescription prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(int prescriptionId) {
        return prescriptionRepository.findById(prescriptionId);
    }

    public ResponseDTO deletePrescription(int prescriptionId) {
        Optional<Prescription> prescription = getPrescriptionById(prescriptionId);
        if (!prescription.isPresent()) {
            return new ResponseDTO(HttpStatus.OK.toString(), "Prescription " + prescriptionId + " not found");
        }

        prescription.get().setDose(null);
        prescriptionRepository.save(prescription.get());
        return new ResponseDTO(HttpStatus.OK.toString(), "Prescription " + prescriptionId + " deleted");
    }

public ResponseDTO save(Prescription prescription) {
    Prescription savedPrescription = prescriptionRepository.save(prescription);
    System.out.println("ID después de guardar: " + savedPrescription.getPrescriptionId()); // Debug
    return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Prescription " + savedPrescription.getPrescriptionId() + " saved");
}

    public ResponseDTO updatePrescription(int prescriptionId, PrescriptionDTO prescriptionDTO) {
        Optional<Prescription> prescription = getPrescriptionById(prescriptionId);
        if (prescription.isPresent()) {
            Prescription existingPrescription = prescription.get();
            existingPrescription.setDose(prescriptionDTO.getDose());
            prescriptionRepository.save(existingPrescription);
            return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Prescription " + prescriptionId + " updated");

        } else {
            return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Prescription " + prescriptionId + " not updated");

        }
    }

    public PrescriptionDTO convertToDTO(Prescription prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        
        prescriptionDTO.setDose(prescription.getDose());
        return prescriptionDTO;
    }

    public Prescription convertToModel(PrescriptionDTO prescriptionDTO) {
    Prescription prescription = new Prescription();
    prescription.setDose(prescriptionDTO.getDose());
    prescription.setSchedule(prescriptionDTO.getSchedule());  // Usa el schedule del DTO
    return prescription;
}


}
