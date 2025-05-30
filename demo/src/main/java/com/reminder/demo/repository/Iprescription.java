package com.reminder.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.reminder.demo.model.Prescription;
import java.util.Optional;
import java.util.List;

public interface Iprescription extends JpaRepository<Prescription, Integer> {
    @Query("SELECT p FROM Prescription p LEFT JOIN FETCH p.patient LEFT JOIN FETCH p.medicine")
    List<Prescription> findAllWithRelations();
}