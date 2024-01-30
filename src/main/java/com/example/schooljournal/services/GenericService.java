package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.BaseEntity;
import com.example.schooljournal.data.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericService<T extends BaseEntity, K extends JpaRepository<T, Long>> {

    GenericService(K repository) {
        this.repository = repository;
    }

    protected K repository;

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to be retrieved.
     * @return The entity with the specified ID.
     * @throws java.util.NoSuchElementException If no entity with the given ID exists.
     */
    public T load(long id) {
        return repository.findById(id).get();
    }

    /**
     * Retrieves a paginated list of all entities.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of entities based on pagination and sorting criteria.
     */
    public List<T> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return getAll(paging);
    }

    /**
     * Retrieves a paginated list of all entities based on the provided {@link Pageable} object.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of entities based on the provided pagination and sorting criteria.
     */
    public List<T> getAll(Pageable paging) {
        Page<T> result = repository.findAll(paging);

        if (result.hasContent()) {
            return result.getContent();
        }
        return new ArrayList<>();
    }

    /**
     * Saves or updates an entity, updating creation and modification details.
     *
     * @param toSave The entity to be saved or updated.
     * @param requester The user initiating the save or update action.
     * @return The saved or updated entity.
     */
    public T save(T toSave, User requester) {
        touch(toSave, requester);
        if (toSave.getId() <= 1) {
            toSave.setCreated(new Date(System.currentTimeMillis()));
        }
        toSave.setEdited(new Date(System.currentTimeMillis()));
        return repository.save(toSave);
    }

    /**
     * Updates the creation and modification details of the entity.
     *
     * @param toSave The entity to be touched.
     * @param requester The user initiating the touch action.
     */
    private void touch(T toSave, User requester) {
        boolean isUserNotNull = null != requester;
        if (toSave.getId() < 1) {
            toSave.setCreated(new Date(System.currentTimeMillis()));
            if (isUserNotNull) {
                toSave.setCreator(requester);
            }
        }
        toSave.setEdited(new Date(System.currentTimeMillis()));
        if (isUserNotNull)
            toSave.setEditor(requester);
    }

    /**
     * Counts the total number of entities.
     *
     * @return The total number of entities.
     */
    public long count() {
        return repository.count();
    }

    /**
     * Marks an entity as deleted and saves the updated entity.
     *
     * @param toDelete The entity to be marked as deleted.
     * @param requester The user initiating the delete action.
     * @return The entity after marking it as deleted.
     */
    public T delete(T toDelete, User requester) {
        toDelete.setDeleted(true);
        return save(toDelete, requester);
    }

    /**
     * Deletes an entity by its ID, marking it as deleted.
     *
     * @param toDeleteId The ID of the entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The entity after marking it as deleted.
     */
    public T delete(long toDeleteId, User requester) {
        return delete(load(toDeleteId), requester);
    }

}