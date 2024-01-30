package com.example.schooljournal.data.entity.school;

import com.example.schooljournal.data.entity.BaseEntity;
import com.example.schooljournal.data.entity.core.Address;
import com.example.schooljournal.data.entity.course.Grade;
import com.example.schooljournal.data.entity.school.enums.SchoolType;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.Director;
import com.example.schooljournal.data.entity.user.Student;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_school")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = School.class)
public class School extends BaseEntity {
    private String name;
    @Enumerated(value = EnumType.STRING)
    private SchoolType schoolType;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne
    private Director director;

    @OneToMany(mappedBy = "school")
    private Set<SchoolClass> classes;
}
