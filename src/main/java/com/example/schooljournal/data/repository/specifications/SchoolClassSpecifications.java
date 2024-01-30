package com.example.schooljournal.data.repository.specifications;

import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.data.entity.user.Student;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class SchoolClassSpecifications {

    public static Specification<SchoolClass> haveStudentWithIds(Set<Long> studentIds) {
        return (root, query, criteriaBuilder) -> {
            Join<SchoolClass, Student> studentJoin = root.join("students");
            return studentJoin.<String>get("id").in(studentIds);
        };
    }

}
