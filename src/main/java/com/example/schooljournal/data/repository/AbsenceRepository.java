package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.course.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
}
