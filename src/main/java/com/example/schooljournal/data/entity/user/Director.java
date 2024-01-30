package com.example.schooljournal.data.entity.user;

import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Director extends User {
    @OneToOne(mappedBy = "director")
    private School school;
}
