package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.school.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
