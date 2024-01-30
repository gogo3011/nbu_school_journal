package com.example.schooljournal.data.entity.user;

import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.course.enums.CourseSubject;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Teacher extends User {
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<CourseSubject> specializedIn;
}
