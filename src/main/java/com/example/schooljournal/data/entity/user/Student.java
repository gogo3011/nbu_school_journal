package com.example.schooljournal.data.entity.user;

import com.example.schooljournal.data.entity.course.Absence;
import com.example.schooljournal.data.entity.course.Grade;
import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Student extends User {
    @ManyToMany
    @JoinTable(
            name = "t_rel_parent_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private Set<Parent> parents;
    @ManyToOne
    @NotNull
    private SchoolClass schoolClass;
}
