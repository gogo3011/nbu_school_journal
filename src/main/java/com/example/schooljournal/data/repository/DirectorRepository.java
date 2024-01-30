package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.user.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
