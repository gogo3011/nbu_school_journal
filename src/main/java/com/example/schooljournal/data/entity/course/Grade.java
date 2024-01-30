package com.example.schooljournal.data.entity.course;

import com.example.schooljournal.data.entity.BaseEntity;
import com.example.schooljournal.data.entity.user.Student;
import com.example.schooljournal.data.entity.user.Teacher;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_grade")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Grade.class)
public class Grade extends BaseEntity {
    @Min(value = 2)
    @Max(value = 6)
    private int grade;
    @ManyToOne
    @NotNull
    private Course course;
    @OneToOne
    @NotNull
    private Student student;
}
