package com.reminder.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.reminder.demo.model.Prescription;
import java.util.Optional;
public interface Iprescription extends JpaRepository<Prescription, Integer> {
@Query("SELECT p FROM Prescription p WHERE p.prescriptionId = ?1")
Optional<Prescription> findById(Integer id);

}
