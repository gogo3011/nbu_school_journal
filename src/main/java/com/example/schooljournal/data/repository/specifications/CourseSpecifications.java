package com.example.schooljournal.data.repository.specifications;

import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.data.entity.user.Student;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class CourseSpecifications {

    public static Specification<Course> haveStudentWithId(long studentId) {
        return (root, query, criteriaBuilder) -> {
            Join<SchoolClass, Course> schoolJoin = root.join("classesWithThisCourse");
            Join<Student, SchoolClass> studentJoin = schoolJoin.join("students");
            return criteriaBuilder.equal(studentJoin.<String>get("id"), studentId);
        };
    }
    public static Specification<Course> haveStudentWithIds(Set<Long> studentIds) {
        return (root, query, criteriaBuilder) -> {
            Join<SchoolClass, Course> schoolJoin = root.join("classesWithThisCourse");
            Join<Student, SchoolClass> studentJoin = schoolJoin.join("students");
            return studentJoin.<String>get("id").in(studentIds);
        };
    }

    public static Specification<Course> haveParentsWithIds(Set<Long> parentIds) {
        return (root, query, criteriaBuilder) -> {
            Join<SchoolClass, Course> schoolJoin = root.join("classesWithThisCourse");
            Join<Student, SchoolClass> studentJoin = schoolJoin.join("students");
            Join<Parent, Student> parentJoin = studentJoin.join("parents");
            return parentJoin.<String>get("id").in(parentIds);
        };
    }
}
