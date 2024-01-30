package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
