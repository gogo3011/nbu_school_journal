package com.example.schooljournal.data.entity.user.enums;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public enum SchoolRole {

    TEACHER(Values.TEACHER), DIRECTOR(Values.DIRECTOR), STUDENT(Values.STUDENT), PARENT(Values.PARENT), ADMIN(Values.ADMIN);

    private String value;

    public String getValue() {
        return value;
    }

    public static class Values {
        public static final String TEACHER = "TEACHER";
        public static final String DIRECTOR = "DIRECTOR";
        public static final String STUDENT = "STUDENT";
        public static final String PARENT = "PARENT";
        public static final String USER = "USER";
        public static final String ADMIN = "ADMIN";
    }
}
