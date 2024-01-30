package com.example.schooljournal.data.repository.specifications;

import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.data.entity.user.Student;
import com.example.schooljournal.data.entity.user.Teacher;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class StudentSpecifications {

    public static Specification<Student> haveParentsWithIds(Set<Long> parentIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Student, Parent> studentJoin = root.join("parents");
            return studentJoin.<String>get("id").in(parentIds);
        };
    }

    public static Specification<Student> haveTeachersWithIds(Set<Long> teacherIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Student, SchoolClass> schoolClassJoin = root.join("schoolClass");
            Join<SchoolClass, Teacher> teacherJoin = schoolClassJoin.join("classLeader");
            return teacherJoin.get("id").in(teacherIds);
        };
    }

    public static Specification<Student> haveSchoolsWithIds(Set<Long> schoolIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Student, SchoolClass> schoolClassJoin = root.join("schoolClass");
            Join<SchoolClass, School> schoolJoin = schoolClassJoin.join("school");
            return schoolJoin.get("id").in(schoolIds);
        };
    }
}
