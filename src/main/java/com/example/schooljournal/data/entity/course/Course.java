package com.example.schooljournal.data.entity.course;

import com.example.schooljournal.data.entity.BaseEntity;
import com.example.schooljournal.data.entity.course.enums.CourseSubject;
import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.Teacher;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_course")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Course.class)
public class Course extends BaseEntity {
    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseSubject courseSubject;

    @NotNull
    @OneToOne
    private School school;

    @ManyToOne
    @NotNull
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name = "t_rel_course_school_class",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "school_class_id")
    )
    private Set<SchoolClass> classesWithThisCourse;
}
