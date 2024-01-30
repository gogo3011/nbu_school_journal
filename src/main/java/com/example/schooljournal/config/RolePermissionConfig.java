package com.example.schooljournal.config;

import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.example.schooljournal.utils.CollectionUtils;

import java.util.*;

public final class RolePermissionConfig {
    private static Map<SchoolRole, Set<Permission>> permissionsMap;

    private RolePermissionConfig() {
    }

    private static void initPermissionMap() {
        permissionsMap = new HashMap<>();
        permissionsMap.put(SchoolRole.ADMIN, CollectionUtils.toSet(Permission.values()));
        permissionsMap.put(SchoolRole.PARENT, CollectionUtils.toSet(Permission.VIEW_COURSES, Permission.VIEW_GRADES, Permission.VIEW_ABSENCE, Permission.VIEW_STUDENTS));
        permissionsMap.put(SchoolRole.STUDENT, CollectionUtils.toSet(Permission.VIEW_COURSES, Permission.VIEW_GRADES, Permission.VIEW_ABSENCE));
        permissionsMap.put(SchoolRole.DIRECTOR, CollectionUtils.toSet(Permission.VIEW_STUDENTS, Permission.VIEW_ALL_SCHOOL_CLASSES_IN_SCHOOL,
                Permission.VIEW_ABSENCE, Permission.VIEW_GRADES, Permission.CREATE_EDIT_COURSES, Permission.CREATE_EDIT_SCHOOL, Permission.CREATE_EDIT_TEACHER));
        permissionsMap.put(SchoolRole.TEACHER, CollectionUtils.toSet(Permission.CREATE_EDIT_GRADE, Permission.CREATE_EDIT_ABSENCE, Permission.VIEW_COURSES, Permission.VIEW_GRADES));
    }

    public static Map<SchoolRole, Set<Permission>> getPermissionsMap() {
        if (null == permissionsMap) {
            initPermissionMap();
        }
        return new HashMap<>(permissionsMap);
    }
}
