package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.course.Absence;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.example.schooljournal.data.repository.AbsenceRepository;
import com.example.schooljournal.services.exceptions.UnauthorizedAction;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService extends GenericService<Absence, AbsenceRepository> {
    AbsenceService(AbsenceRepository repository) {
        super(repository);
    }

    /**
     * Loads an Absence entity by its ID.
     *
     * @param id The ID of the Absence entity to be loaded.
     * @return The Absence entity with the specified ID.
     */
    @Override
    public Absence load(long id) {
        return super.load(id);
    }

    /**
     * Retrieves a paginated list of all Absence entities, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of Absence entities based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_ABSENCE.getAuthority())")
    public List<Absence> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Retrieves a paginated list of all Absence entities, requiring the user to have the necessary permissions.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of Absence entities based on the provided pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_ABSENCE.getAuthority())")
    public List<Absence> getAll(Pageable paging) {
        return super.getAll(paging);
    }

    /**
     * Saves or updates an Absence entity, validating teacher rights and requiring the user to have the necessary permissions.
     *
     * @param toSave The Absence entity to be saved or updated.
     * @param requester The user making the request to save or update the Absence.
     * @return The saved or updated Absence entity.
     * @throws UnauthorizedAction If the user does not have the necessary permissions or if the course is not taught by the teacher.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_ABSENCE.getAuthority())")
    public Absence save(Absence toSave, User requester) {
        validateTeacherRights(toSave, requester);
        return super.save(toSave, requester);
    }

    /**
     * Counts the total number of Absence entities.
     *
     * @return The total number of Absence entities.
     */
    @Override
    public long count() {
        return super.count();
    }

    /**
     * Marks an Absence entity as deleted and saves the updated entity, validating teacher rights and requiring the user to have the necessary permissions.
     *
     * @param toDelete The Absence entity to be marked as deleted.
     * @param requester The user making the request to delete the Absence.
     * @return The Absence entity after marking it as deleted.
     * @throws UnauthorizedAction If the user does not have the necessary permissions or if the course is not taught by the teacher.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_ABSENCE.getAuthority())")
    public Absence delete(Absence toDelete, User requester) {
        validateTeacherRights(toDelete, requester);
        return super.delete(toDelete, requester);
    }

    /**
     * Deletes an Absence entity by its ID, marking it as deleted and saving the updated entity, validating teacher rights and requiring the user to have the necessary permissions.
     *
     * @param toDeleteId The ID of the Absence entity to be deleted.
     * @param requester The user making the request to delete the Absence.
     * @return The Absence entity after marking it as deleted.
     * @throws UnauthorizedAction If the user does not have the necessary permissions or if the course is not taught by the teacher.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_ABSENCE.getAuthority())")
    public Absence delete(long toDeleteId, User requester) {
        validateTeacherRights(load(toDeleteId), requester);
        return super.delete(toDeleteId, requester);
    }

    /**
     * Validates teacher rights for creating or editing an Absence entity.
     *
     * @param toSave The Absence entity to be validated.
     * @param requester The user making the request to save or edit the Absence.
     * @throws UnauthorizedAction If the user does not have the necessary permissions or if the course is not taught by the teacher.
     */
    private void validateTeacherRights(Absence toSave, User requester) {
        if (SchoolRole.TEACHER.equals(requester.getSchoolRole())) {
            if (toSave.getCourse().getTeacher().getId() != requester.getId()) {
                throw new UnauthorizedAction("Course is not taught by this teacher");
            }
            if (toSave.getId() > 0) {
                Absence savedInDb = load(toSave.getId());
                if (savedInDb.getCreator().getId() != requester.getId()) {
                    throw new UnauthorizedAction("Absence is not created by this teacher");
                }
            }
        }
    }
}
