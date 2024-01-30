package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

    List<Course> getCoursesByTeacherId(long teacherId, Pageable page);
    List<Course> getCoursesBySchoolId(long schoolId, Pageable page);
}
