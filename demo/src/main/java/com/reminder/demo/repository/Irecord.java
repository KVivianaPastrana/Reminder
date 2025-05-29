package com.reminder.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.reminder.demo.model.Record;
import java.util.Optional;

public interface Irecord extends JpaRepository<Record, Integer> {
@Query("SELECT r FROM Record r WHERE r.recordId = ?1")
Optional<Record> findById(Integer id);

}
