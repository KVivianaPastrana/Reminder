package com.reminder.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.reminder.demo.service.MedicineService;
import com.reminder.demo.model.Medicin;
import com.reminder.demo.DTO.MedicineDTO;
import com.reminder.demo.DTO.ResponseDTO;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<Medicin> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<Object> getMedicineById(@PathVariable int medicineId) {
        var medicine = medicineService.getMedicineById(medicineId);
        if (medicine.isPresent()) {
            return new ResponseEntity<>(medicineService.convertToDTO(medicine.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
public ResponseEntity<Object> createMedicine(@RequestBody MedicineDTO medicineDTO) {
    // Puedes usar cualquiera de estos:
    ResponseDTO respuesta = medicineService.save(medicineDTO); // Opción preferida
    // O:
    // ResponseDTO respuesta = medicineService.saveEntity(medicineService.convertToModel(medicineDTO));
    return new ResponseEntity<>(respuesta, HttpStatus.OK);
}

    @PutMapping("/{medicineId}")
    public ResponseEntity<Object> updateMedicine(@PathVariable int medicineId, @RequestBody MedicineDTO medicineDTO) {
        ResponseDTO respuesta = medicineService.updateMedicine(medicineId, medicineDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<Object> deleteMedicine(@PathVariable int medicineId) {
        ResponseDTO respuesta = medicineService.deleteMedicine(medicineId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
