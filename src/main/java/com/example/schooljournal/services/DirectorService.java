package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.user.Director;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.repository.DirectorRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService extends GenericService<Director, DirectorRepository> {
    public DirectorService(DirectorRepository repository) {
        super(repository);
    }


    /**
     * Loads a Director entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param id The ID of the Director entity to be loaded.
     * @return The Director entity with the specified ID.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_PRINCIPALS.getAuthority())")
    public Director load(long id) {
        return super.load(id);
    }

    /**
     * Retrieves a paginated list of all Director entities, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of Director entities based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_PRINCIPALS.getAuthority())")
    public List<Director> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Retrieves a paginated list of all Director entities, requiring the user to have the necessary permissions.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of Director entities based on the provided pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_PRINCIPALS.getAuthority())")
    public List<Director> getAll(Pageable paging) {
        return super.getAll(paging);
    }

    /**
     * Deletes a Director entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param toDeleteId The ID of the Director entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Director entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_PRINCIPAL.getAuthority())")
    public Director delete(long toDeleteId, User requester) {
        return super.delete(toDeleteId, requester);
    }

    /**
     * Deletes a Director entity, requiring the user to have the necessary permissions.
     *
     * @param toDelete The Director entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Director entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_PRINCIPAL.getAuthority())")
    public Director delete(Director toDelete, User requester) {
        return super.delete(toDelete, requester);
    }

    /**
     * Saves or updates a Director entity, requiring the user to have the necessary permissions.
     *
     * @param toSave The Director entity to be saved or updated.
     * @param requester The user initiating the save or update action.
     * @return The saved or updated Director entity.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_PRINCIPAL.getAuthority())")
    public Director save(Director toSave, User requester) {
        return super.save(toSave, requester);
    }
}
