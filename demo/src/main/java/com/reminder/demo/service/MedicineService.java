package com.reminder.demo.service;

import com.reminder.demo.model.Medicin;
import com.reminder.demo.repository.Imedicine;
import com.reminder.demo.DTO.MedicineDTO;
import com.reminder.demo.DTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    private Imedicine medicineRepository;

    public List<Medicin> getAllMedicines() {
        return medicineRepository.findByStatusTrue();
    }

    public Optional<Medicin> getMedicineById(int medicineId) {
        return medicineRepository.findById(medicineId);
    }

    public ResponseDTO deleteMedicine(int medicineId) {
        try {
            Optional<Medicin> medicine = getMedicineById(medicineId);
            if (!medicine.isPresent()) {
                return new ResponseDTO("Medicine not found", false, null);
            }

            medicine.get().setStatus(false);
            medicineRepository.save(medicine.get());
            return new ResponseDTO("Medicine deleted", true, null);
        } catch (Exception e) {
            return new ResponseDTO("Error deleting medicine", false, null);
        }
    }
public ResponseDTO save(MedicineDTO medicineDTO) {
    Medicin medicine = convertToModel(medicineDTO);
    return saveEntity(medicine);
}

public ResponseDTO saveEntity(Medicin medicine) {
    try {
        Medicin savedMedicine = medicineRepository.save(medicine);
        return new ResponseDTO(
            "Medicine saved successfully",
            true,
            convertToDTO(savedMedicine)
        );
    } catch (Exception e) {
        return new ResponseDTO(
            "Error saving medicine: " + e.getMessage(),
            false,
            null
        );
    }
}


    public ResponseDTO updateMedicine(int medicineId, MedicineDTO medicineDTO) {
    try {
        Optional<Medicin> medicine = getMedicineById(medicineId);
        if (medicine.isPresent()) {
            Medicin existingMedicine = medicine.get();
            existingMedicine.setMedicineName(medicineDTO.getMedicineName());
            
            // Línea corregida:
            if (medicineDTO.getStatus() != null) {
                existingMedicine.setStatus(medicineDTO.getStatus());
            }
            
            medicineRepository.save(existingMedicine);
            return new ResponseDTO(
                "Medicine updated successfully",
                true,
                convertToDTO(existingMedicine)
            );
        }
        return new ResponseDTO("Medicine not found", false, null);
    } catch (Exception e) {
        return new ResponseDTO(
            "Error updating medicine: " + e.getMessage(),
            false,
            null
        );
    }
}
    public Medicin convertToModel(MedicineDTO medicineDTO) {
    Medicin medicine = new Medicin();
    medicine.setMedicineName(medicineDTO.getMedicineName());
    medicine.setStatus(medicineDTO.getStatus());
    return medicine;
}

public MedicineDTO convertToDTO(Medicin medicine) {
    MedicineDTO dto = new MedicineDTO();
    dto.setMedicineName(medicine.getMedicineName());
    dto.setStatus(medicine.getStatus());
    return dto;
}
}
