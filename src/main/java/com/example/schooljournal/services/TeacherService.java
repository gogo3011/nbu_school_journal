package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.user.Teacher;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.example.schooljournal.data.repository.TeacherRepository;
import com.example.schooljournal.services.exceptions.EmailAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService extends GenericService<Teacher, TeacherRepository> {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    public TeacherService(TeacherRepository repository) {
        super(repository);
    }

    /**
     * Saves or updates a Teacher entity, requiring the user to have the necessary permissions.
     *
     * @param toSave The Teacher entity to be saved or updated.
     * @param requester The user initiating the save or update action.
     * @return The saved or updated Teacher entity.
     * @throws IllegalArgumentException If the provided Teacher doesn't have the 'Teacher' school role.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_TEACHER.getAuthority())")
    public Teacher save(Teacher toSave, User requester) {
        if (!SchoolRole.TEACHER.equals(toSave.getSchoolRole())) {
            throw new IllegalArgumentException("Teacher to save doesn't have 'Teacher' school role");
        }
        User authUser;
        try {
            authUser = authService.registerUser(toSave);
        } catch (EmailAlreadyRegisteredException e) {
            authUser = userService.loadUserByUsername(toSave.getEmail());
        }
        toSave.setId(authUser.getId());
        return super.save(toSave, requester);
    }

    /**
     * Retrieves a paginated list of all Teacher entities, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of Teacher entities based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_TEACHERS.getAuthority())")
    public List<Teacher> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Retrieves a paginated list of all Teacher entities, requiring the user to have the necessary permissions.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of Teacher entities based on the provided pagination and sorting criteria.
     */
    @Override
    public List<Teacher> getAll(Pageable paging) {
        return super.getAll(paging);
    }
}
