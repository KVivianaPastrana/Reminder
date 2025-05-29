package com.reminder.demo.service;

import com.reminder.demo.model.Medicin;
import com.reminder.demo.repository.Imedicine;
import com.reminder.demo.DTO.MedicineDTO;
import com.reminder.demo.DTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MedicineService {

    @Autowired
    private Imedicine medicineRepository;

    public List<Medicin> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Optional<Medicin> getMedicineById(int medicineId) {
        return medicineRepository.findById(medicineId);
    }

    public ResponseDTO deleteMedicine(int medicineId) {
        Optional<Medicin> medicine = getMedicineById(medicineId);
        if (!medicine.isPresent()) {
            return new ResponseDTO(HttpStatus.OK.toString(), "Medicine " + medicineId + " not found");
        }

        medicine.get().setStatus(false);
        medicineRepository.save(medicine.get());
        return new ResponseDTO(HttpStatus.OK.toString(), "Medicine " + medicineId + " deleted");
    }

public ResponseDTO save(Medicin medicine) {
    Medicin savedMedicine = medicineRepository.save(medicine);
    System.out.println("ID después de guardar: " + savedMedicine.getMedicineId()); // Debug
    return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Medicine " + savedMedicine.getMedicineId() + " saved");
}

    public ResponseDTO updateMedicine(int medicineId, MedicineDTO medicineDTO) {
        Optional<Medicin> medicine = getMedicineById(medicineId);
        if (medicine.isPresent()) {
            Medicin existingMedicine = medicine.get();
            existingMedicine.setMedicineName(medicineDTO.getMedicineName());
            medicineRepository.save(existingMedicine);
            return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Medicine " + medicineId + " updated");

        } else {
            return new ResponseDTO(
        HttpStatus.OK.toString(),
        "Medicine " + medicineId + " not updated");

        }
    }

    public MedicineDTO convertToDTO(Medicin medicine) {
        MedicineDTO medicineDTO = new MedicineDTO();
        
        medicineDTO.setMedicineName(medicine.getMedicineName());
        return medicineDTO;
    }

    public Medicin convertToModel(MedicineDTO medicineDTO) {
    Medicin medicine = new Medicin();
    medicine.setMedicineName(medicineDTO.getMedicineName());
    medicine.setStatus(true); // Deja que JPA asigne el ID automáticamente
    return medicine;
}
}
