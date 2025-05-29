package com.reminder.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.reminder.demo.model.Medicin;
import java.util.Optional;

public interface Imedicine extends JpaRepository<Medicin, Integer> {
@Query("SELECT m FROM Medicin m WHERE m.medicineId = ?1")
Optional<Medicin> findById(Integer id);                

}
