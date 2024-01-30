package com.example.schooljournal.config;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    CREATE_EDIT_SCHOOL,
    CREATE_EDIT_STUDENT,
    CREATE_EDIT_PRINCIPAL,
    CREATE_EDIT_PARENTS,
    CREATE_EDIT_COURSES,
    CREATE_EDIT_COURSES_FOR_ALL_TEACHERS,
    CREATE_EDIT_GRADE,
    CREATE_EDIT_SCHOOL_CLASS,
    CREATE_EDIT_TEACHER,
    CREATE_EDIT_ABSENCE,
    VIEW_SCHOOLS,
    VIEW_COURSES,
    VIEW_STUDENTS,
    VIEW_SCHOOL_CLASSES,
    VIEW_ALL_SCHOOL_CLASSES_IN_SCHOOL,
    VIEW_GRADES,
    VIEW_TEACHERS,
    VIEW_PARENTS,
    VIEW_ABSENCE,
    VIEW_ALL_COURSES,
    VIEW_ALL_STUDENTS,
    VIEW_ALL_SCHOOL_CLASSES,
    VIEW_ALL_GRADES,
    VIEW_ALL_TEACHERS,
    VIEW_ALL_PARENTS,
    VIEW_ALL_ABSENCE,

    VIEW_ALL_PRINCIPALS;


    @Override
    public String getAuthority() {
        return this.name();
    }
}
