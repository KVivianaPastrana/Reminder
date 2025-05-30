package com.reminder.demo.repository;

import com.reminder.demo.model.Medicin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Imedicine extends JpaRepository<Medicin, Integer> {
    List<Medicin> findByStatusTrue();
}
