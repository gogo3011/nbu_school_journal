package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.repository.ParentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService extends GenericService<Parent, ParentRepository> {
    public ParentService(ParentRepository repository) {
        super(repository);
    }

    /**
     * Loads a Parent entity by its ID.
     *
     * @param id The ID of the Parent entity to be loaded.
     * @return The Parent entity with the specified ID.
     */
    @Override
    public Parent load(long id) {
        return super.load(id);
    }

    /**
     * Retrieves a paginated list of all Parent entities, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of Parent entities based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_PARENTS.getAuthority())")
    public List<Parent> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Retrieves a paginated list of all Parent entities, requiring the user to have the necessary permissions.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of Parent entities based on the provided pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_PARENTS.getAuthority())")
    public List<Parent> getAll(Pageable paging) {
        return super.getAll(paging);
    }

    /**
     * Saves or updates a Parent entity, requiring the user to have the necessary permissions.
     *
     * @param toSave The Parent entity to be saved or updated.
     * @param requester The user initiating the save or update action.
     * @return The saved or updated Parent entity.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_PARENTS.getAuthority())")
    public Parent save(Parent toSave, User requester) {
        return super.save(toSave, requester);
    }

    /**
     * Counts the total number of Parent entities.
     *
     * @return The total number of Parent entities.
     */
    @Override
    public long count() {
        return super.count();
    }

    /**
     * Deletes a Parent entity, requiring the user to have the necessary permissions.
     *
     * @param toDelete The Parent entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Parent entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_PARENTS.getAuthority())")
    public Parent delete(Parent toDelete, User requester) {
        return super.delete(toDelete, requester);
    }

    /**
     * Deletes a Parent entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param toDeleteId The ID of the Parent entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Parent entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_PARENTS.getAuthority())")
    public Parent delete(long toDeleteId, User requester) {
        return super.delete(toDeleteId, requester);
    }
}
