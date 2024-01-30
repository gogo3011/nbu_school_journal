package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.repository.SchoolRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService extends GenericService<School, SchoolRepository> {
    public SchoolService(SchoolRepository repository) {
        super(repository);
    }

    /**
     * Loads a School entity by its ID.
     *
     * @param id The ID of the School entity to be loaded.
     * @return The School entity with the specified ID.
     */
    @Override
    public School load(long id) {
        return super.load(id);
    }

    /**
     * Retrieves a paginated list of all School entities, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of School entities based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_SCHOOLS.getAuthority())")
    public List<School> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Retrieves a paginated list of all School entities, requiring the user to have the necessary permissions.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of School entities based on the provided pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_SCHOOLS.getAuthority())")
    public List<School> getAll(Pageable paging) {
        return super.getAll(paging);
    }

    /**
     * Saves or updates a School entity, requiring the user to have the necessary permissions.
     *
     * @param toSave The School entity to be saved or updated.
     * @param requester The user initiating the save or update action.
     * @return The saved or updated School entity.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_SCHOOL.getAuthority())")
    public School save(School toSave, User requester) {
        return super.save(toSave, requester);
    }

    /**
     * Counts the total number of School entities.
     *
     * @return The total number of School entities.
     */
    @Override
    public long count() {
        return super.count();
    }

    /**
     * Deletes a School entity, requiring the user to have the necessary permissions.
     *
     * @param toDelete The School entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The School entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_SCHOOL.getAuthority())")
    public School delete(School toDelete, User requester) {
        return super.delete(toDelete, requester);
    }

    /**
     * Deletes a School entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param toDeleteId The ID of the School entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The School entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_SCHOOL.getAuthority())")
    public School delete(long toDeleteId, User requester) {
        return super.delete(toDeleteId, requester);
    }
}
