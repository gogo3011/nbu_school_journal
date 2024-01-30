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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_absence")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Absence.class)
public class Absence extends BaseEntity {
    @NotNull
    private Date date;
    @OneToOne
    private Teacher teacher;
    @OneToOne
    private Student student;
    @OneToOne
    private Course course;
}
