package com.example.schooljournal.data.entity;

import com.example.schooljournal.data.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode
@Where(clause = "deleted=false")
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date created;
    private Date edited;
    @ManyToOne
    private User editor;
    @ManyToOne
    private User creator;
    private boolean deleted = false;
}
