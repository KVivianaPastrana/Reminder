package com.reminder.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.reminder.demo.model.Prescription;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface Iprescription extends JpaRepository<Prescription, Integer> {
    
    // Método existente (lo mantienes)
    @Query("SELECT p FROM Prescription p LEFT JOIN FETCH p.patient LEFT JOIN FETCH p.medicine")
    List<Prescription> findAllWithRelations();
    
    // 🔥 Añade este nuevo método para buscar por hora programada

@Query("SELECT p FROM Prescription p " +
       "LEFT JOIN FETCH p.patient " +
       "LEFT JOIN FETCH p.medicine " +
       "WHERE p.schedule >= :start AND p.schedule < :end AND p.notified = false")
List<Prescription> findPendingBetween(LocalTime start, LocalTime end);
    List<Prescription> findByConfirmedFalseAndReminderSentAtBefore(LocalDateTime limite);


}