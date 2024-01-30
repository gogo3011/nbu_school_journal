package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long>, JpaSpecificationExecutor<SchoolClass> {

    List<SchoolClass> findSchoolClassByClassLeaderId(Long id, Pageable pageable);
    List<SchoolClass> findSchoolClassBySchoolId(Long id, Pageable pageable);

}
