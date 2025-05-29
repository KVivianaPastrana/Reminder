package com.reminder.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.reminder.demo.model.Patient;
import java.util.Optional;
public interface Ipatient  extends JpaRepository<Patient, Integer> {
@Query("SELECT p FROM Patient p WHERE p.patientId = ?1")
Optional<Patient> findById(Integer id);

}
