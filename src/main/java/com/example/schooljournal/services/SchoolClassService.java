package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.repository.SchoolClassRepository;
import com.example.schooljournal.data.repository.specifications.SchoolClassSpecifications;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SchoolClassService extends GenericService<SchoolClass, SchoolClassRepository> {
    public SchoolClassService(SchoolClassRepository repository) {
        super(repository);
    }

    /**
     * Retrieves a paginated list of school classes associated with specific students, requiring the user to have the necessary permissions.
     *
     * @param studentIds The set of IDs of students for whom school classes are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of SchoolClass objects associated with the specified students.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_SCHOOL_CLASSES.getAuthority())")
    public List<SchoolClass> listSchoolClassForStudentIds(Set<Long> studentIds, Pageable pageable) {
        return repository.findAll(SchoolClassSpecifications.haveStudentWithIds(studentIds), pageable).toList();
    }

    /**
     * Retrieves a paginated list of school classes associated with a specific class leader, requiring the user to have the necessary permissions.
     *
     * @param leaderId The ID of the class leader for whom school classes are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of SchoolClass objects associated with the specified class leader.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_SCHOOL_CLASSES.getAuthority())")
    public List<SchoolClass> listSchoolClassForClassLeader(Long leaderId, Pageable pageable) {
        return repository.findSchoolClassByClassLeaderId(leaderId, pageable);
    }

    /**
     * Retrieves a paginated list of school classes associated with a specific school, requiring the user to have the necessary permissions.
     *
     * @param schoolId The ID of the school for which school classes are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of SchoolClass objects associated with the specified school.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_SCHOOL_CLASSES_IN_SCHOOL.getAuthority())")
    public List<SchoolClass> listSchoolClassForSchoolId(Long schoolId, Pageable pageable) {
        return repository.findSchoolClassBySchoolId(schoolId, pageable);
    }

    /**
     * Loads a SchoolClass entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param id The ID of the SchoolClass entity to be loaded.
     * @return The SchoolClass entity with the specified ID.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_SCHOOL_CLASSES.getAuthority())")
    public SchoolClass load(long id) {
        return super.load(id);
    }

    /**
     * Retrieves a paginated list of all SchoolClass entities, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of SchoolClass entities based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_SCHOOL_CLASSES.getAuthority())")
    public List<SchoolClass> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Retrieves a paginated list of all SchoolClass entities, requiring the user to have the necessary permissions.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of SchoolClass entities based on the provided pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_SCHOOL_CLASSES.getAuthority())")
    public List<SchoolClass> getAll(Pageable paging) {
        return super.getAll(paging);
    }

    /**
     * Saves or updates a SchoolClass entity, requiring the user to have the necessary permissions.
     *
     * @param toSave The SchoolClass entity to be saved or updated.
     * @param requester The user initiating the save or update action.
     * @return The saved or updated SchoolClass entity.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_SCHOOL_CLASS.getAuthority())")
    public SchoolClass save(SchoolClass toSave, User requester) {
        return super.save(toSave, requester);
    }

    /**
     * Counts the total number of SchoolClass entities.
     *
     * @return The total number of SchoolClass entities.
     */
    @Override
    public long count() {
        return super.count();
    }

    /**
     * Deletes a SchoolClass entity, requiring the user to have the necessary permissions.
     *
     * @param toDelete The SchoolClass entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The SchoolClass entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_SCHOOL_CLASS.getAuthority())")
    public SchoolClass delete(SchoolClass toDelete, User requester) {
        return super.delete(toDelete, requester);
    }

    /**
     * Deletes a SchoolClass entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param toDeleteId The ID of the SchoolClass entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The SchoolClass entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_SCHOOL_CLASS.getAuthority())")
    public SchoolClass delete(long toDeleteId, User requester) {
        return super.delete(toDeleteId, requester);
    }
}
