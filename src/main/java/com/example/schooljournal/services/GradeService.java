package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.course.Grade;
import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.data.entity.user.Teacher;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.example.schooljournal.data.repository.GradeRepository;
import com.example.schooljournal.services.exceptions.UnauthorizedAction;
import com.example.schooljournal.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.nio.file.AccessDeniedException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class GradeService extends GenericService<Grade, GradeRepository>{

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    public GradeService(GradeRepository repository) {
        super(repository);
    }

    /**
     * Retrieves a paginated list of grades for a specific course.
     *
     * @param courseIds Set of IDs of the courses for which grades are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Grade objects corresponding to the specified course.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_GRADES.getAuthority())")
    public List<Grade> getGradesByCourseIds(Set<Long> courseIds, Pageable pageable) {
        return repository.getGradesByCourseIdIn(courseIds, pageable);
    }

    /**
     * Retrieves a paginated list of grades for specific students.
     *
     * @param studentIds The set of IDs of students for whom grades are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Grade objects corresponding to the specified students.
     */
    public List<Grade> getGradesByStudentIds(Set<Long> studentIds, Pageable pageable) {
        return repository.getGradesByStudentIdIn(studentIds, pageable);
    }

    /**
     * Retrieves a paginated list of grades for specific students in a particular course.
     *
     * @param studentIds The set of IDs of students for whom grades are requested.
     * @param courseId The ID of the course for which grades are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Grade objects corresponding to the specified students in the specified course.
     */
    public List<Grade> getGradesByStudentIdsAndCourseId(Set<Long> studentIds, Long courseId, Pageable pageable) {
        return repository.getGradesByStudentIdInAndCourseId(studentIds, courseId, pageable);
    }

    /**
     * Retrieves a list of grades for a specific course based on the user's role.
     *
     * @param courseId The ID of the course for which grades are requested.
     * @param user The user making the request.
     * @param page The Pageable object specifying pagination and sorting criteria.
     * @return A list of Grade objects corresponding to the specified course.
     * @throws UnauthorizedAction If the user does not have the necessary permissions to access grades.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_GRADES.getAuthority())")
    public List<Grade> getGradesForCourse(Long courseId, User user, Pageable page) {
        switch (user.getSchoolRole()) {
            case ADMIN, DIRECTOR, TEACHER -> {
                // Check if course is taught by current teacher
                if (SchoolRole.TEACHER.equals(user.getSchoolRole())) {
                    Course course = courseService.load(courseId);
                    if (course.getTeacher().getId() != user.getId()) {
                        throw new UnauthorizedAction("Course id not accessible by teacher");
                    }
                }
                return getGradesByCourseIds(CollectionUtils.toSet(courseId), page);
            }
            case STUDENT, PARENT -> {
                Set<Long> studentIds = new HashSet<>();
                if (user.getSchoolRole().equals(SchoolRole.STUDENT)) {
                    studentIds.add(user.getId());
                } else {
                    studentService.getStudentsForParentIds(CollectionUtils.toSet(user.getId()), Pageable.unpaged()).forEach(s -> studentIds.add(s.getId()));
                }
                return getGradesByStudentIdsAndCourseId(studentIds, courseId, page);
            }
        }
        throw new UnauthorizedAction("User has no access to grades");
    }

    /**
     * Retrieves a paginated list of all grades, ensuring the user has the necessary permissions.
     *
     * @param page The Pageable object specifying pagination and sorting criteria.
     * @return A list of Grade objects based on the provided pagination and sorting criteria.
     * @throws UnauthorizedAction If the user does not have the necessary permissions to view all grades.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_GRADES.getAuthority())")
    public List<Grade> getAll(Pageable page) {
        return super.getAll(page);
    }

    /**
     * Overrides the base save method to create or edit a grade, ensuring the user has the necessary permissions.
     *
     * @param grade The Grade object to be saved or edited.
     * @param requester The user making the request to save or edit the grade.
     * @return The saved or edited Grade object.
     * @throws UnauthorizedAction If the course is not accessible by the current teacher.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_GRADE.getAuthority())")
    public Grade save(Grade grade, User requester) throws UnauthorizedAction {
        validateTeacherRights(grade, requester);
        return super.save(grade, requester);
    }

    /**
     * Loads a Grade entity by its ID.
     *
     * @param id The ID of the Grade entity to be loaded.
     * @return The Grade entity with the specified ID.
     */
    @Override
    public Grade load(long id) {
        return super.load(id);
    }

    /**
     * Retrieves a paginated list of all Grade entities, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of Grade entities based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_GRADES.getAuthority())")
    public List<Grade> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Counts the total number of Grade entities.
     *
     * @return The total number of Grade entities.
     */
    @Override
    public long count() {
        return super.count();
    }

    /**
     * Deletes a Grade entity, requiring the user to have the necessary permissions.
     *
     * @param toDelete The Grade entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Grade entity after deletion.
     * @throws UnauthorizedAction If the requester doesn't have the necessary rights to delete the Grade.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_GRADE.getAuthority())")
    public Grade delete(Grade toDelete, User requester) {
        validateTeacherRights(toDelete, requester);
        return super.delete(toDelete, requester);
    }

    /**
     * Deletes a Grade entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param toDeleteId The ID of the Grade entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Grade entity after deletion.
     * @throws UnauthorizedAction If the requester doesn't have the necessary rights to delete the Grade.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_GRADE.getAuthority())")
    public Grade delete(long toDeleteId, User requester) {
        validateTeacherRights(load(toDeleteId), requester);
        return super.delete(toDeleteId, requester);
    }

    /**
     * Validates that the requester has the necessary rights to perform actions on the Grade entity.
     *
     * @param grade The Grade entity for which rights are being validated.
     * @param requester The user initiating the action.
     * @throws UnauthorizedAction If the requester doesn't have the necessary rights to perform actions on the Grade.
     */
    private void validateTeacherRights(Grade grade, User requester) {
        if (Objects.requireNonNull(requester.getSchoolRole()) == SchoolRole.TEACHER) {
            // Check if course is taught by current teacher
            Course course = courseService.load(grade.getCourse().getId());
            if (course.getTeacher().getId() != requester.getId()) {
                throw new UnauthorizedAction("Course id not accessible by teacher");
            }
        }
    }
}
