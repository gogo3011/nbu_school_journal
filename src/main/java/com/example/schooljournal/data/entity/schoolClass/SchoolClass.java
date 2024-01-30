package com.example.schooljournal.data.entity.schoolClass;

import com.example.schooljournal.data.entity.BaseEntity;
import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.user.Student;
import com.example.schooljournal.data.entity.user.Teacher;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_school_class")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = SchoolClass.class)
public class SchoolClass extends BaseEntity {
    private Date classOf;
    @NotNull
    private char letter;
    @ManyToOne
    private School school;
    @OneToMany(mappedBy = "schoolClass")
    private Set<Student> students;
    @ManyToOne
    private Teacher classLeader;
    @ManyToMany(mappedBy = "classesWithThisCourse")
    private Set<Course> taughtCourses;
}
