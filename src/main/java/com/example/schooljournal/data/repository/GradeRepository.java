package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.course.Grade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface GradeRepository extends JpaRepository<Grade, Long>, JpaSpecificationExecutor<Grade> {
    List<Grade> getGradesByStudentId(long studentId, Pageable pageable);
    List<Grade> getGradesByStudentIdAndCourseId(long studentId, long courseId, Pageable pageable);
    List<Grade> getGradesByCourseIdIn(Set<Long> courseId, Pageable pageable);

    List<Grade> getGradesByStudentIdIn(Set<Long> studentId, Pageable pageable);
    List<Grade> getGradesByStudentIdInAndCourseId(Set<Long> studentId, long courseId, Pageable pageable);
}