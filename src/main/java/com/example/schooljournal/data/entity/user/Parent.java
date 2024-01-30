package com.example.schooljournal.data.entity.user;

import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Parent extends User {
    @ManyToMany(mappedBy = "parents")
    Set<Student> students;
}
