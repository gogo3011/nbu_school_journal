package com.example.schooljournal.services;

import com.example.schooljournal.config.Permission;
import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.user.Teacher;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.repository.CourseRepository;
import com.example.schooljournal.data.repository.specifications.CourseSpecifications;
import com.example.schooljournal.services.exceptions.UnauthorizedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseService extends GenericService<Course, CourseRepository>{
    @Autowired
    private TeacherService teacherService;

    public CourseService(CourseRepository repository) {
        super(repository);
    }

    /**
     * Retrieves a paginated list of courses associated with a specific student, requiring the user to have the necessary permissions.
     *
     * @param studentId The ID of the student for whom courses are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Course objects associated with the specified student.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_COURSES.getAuthority())")
    public List<Course> getCoursesForStudentId(long studentId, Pageable pageable) {
        return repository.findAll(CourseSpecifications.haveStudentWithId(studentId), pageable).toList();
    }

    /**
     * Retrieves a paginated list of courses associated with specific students, requiring the user to have the necessary permissions.
     *
     * @param studentIds The set of IDs of students for whom courses are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Course objects associated with the specified students.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_COURSES.getAuthority())")
    public List<Course> getCoursesForStudentIds(Set<Long> studentIds, Pageable pageable) {
        return repository.findAll(CourseSpecifications.haveStudentWithIds(studentIds), pageable).toList();
    }

    /**
     * Retrieves a paginated list of courses associated with specific parents, requiring the user to have the necessary permissions.
     *
     * @param parentIds The set of IDs of parents for whom courses are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Course objects associated with the specified parents.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_COURSES.getAuthority())")
    public List<Course> getCoursesForParentIds(Set<Long> parentIds, Pageable pageable) {
        return repository.findAll(CourseSpecifications.haveParentsWithIds(parentIds), pageable).toList();
    }

    /**
     * Retrieves a paginated list of courses associated with a specific teacher, requiring the user to have the necessary permissions.
     *
     * @param teacherId The ID of the teacher for whom courses are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Course objects associated with the specified teacher.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_COURSES.getAuthority())")
    public List<Course> getCoursesForTeacherId(long teacherId, Pageable pageable) {
        return repository.getCoursesByTeacherId(teacherId, pageable);
    }

    /**
     * Retrieves a paginated list of courses associated with a specific school, requiring the user to have the necessary permissions.
     *
     * @param schoolId The ID of the school for which courses are requested.
     * @param pageable The Pageable object specifying pagination and sorting criteria.
     * @return A list of Course objects associated with the specified school.
     */
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_COURSES.getAuthority())")
    public List<Course> getCoursesForSchoolId(long schoolId, Pageable pageable) {
        return repository.getCoursesBySchoolId(schoolId, pageable);
    }

    /**
     * Overrides the base save method to create or edit a course, ensuring the user has the necessary permissions.
     *
     * @param course The Course object to be saved or edited.
     * @param requester The user initiating the save or edit action.
     * @return The saved or edited Course object.
     * @throws UnauthorizedAction If the user does not have the necessary permissions or if creating a course for a different teacher without permission.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_COURSES.getAuthority())")
    public Course save(Course course, User requester) throws UnauthorizedAction {
        Teacher teacher = teacherService.load(course.getTeacher().getId());
        if (!requester.hasPermission(Permission.CREATE_EDIT_COURSES_FOR_ALL_TEACHERS)) {
            if (!teacher.getSpecializedIn().contains(course.getCourseSubject())) {
                throw new UnauthorizedAction("Teacher is not specialized in the subject");
            }
            if (course.getTeacher().getId() != teacher.getId()) {
                throw new UnauthorizedAction("User has no permission to create course for different teacher");
            }
        }
        return super.save(course, requester);
    }

    /**
     * Loads a Course entity by its ID.
     *
     * @param id The ID of the Course entity to be loaded.
     * @return The Course entity with the specified ID.
     */
    @Override
    public Course load(long id) {
        return super.load(id);
    }

    /**
     * Retrieves a paginated list of all courses, requiring the user to have the necessary permissions.
     *
     * @param pageNo The page number (starting from 0).
     * @param pageSize The size of each page.
     * @param sortBy The property to sort the result by.
     * @return A list of Course objects based on pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_COURSES.getAuthority())")
    public List<Course> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    /**
     * Retrieves a paginated list of all courses, requiring the user to have the necessary permissions.
     *
     * @param paging The Pageable object specifying pagination and sorting criteria.
     * @return A list of Course objects based on the provided pagination and sorting criteria.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_COURSES.getAuthority())")
    public List<Course> getAll(Pageable paging) {
        return super.getAll(paging);
    }

    /**
     * Counts the total number of Course entities.
     *
     * @return The total number of Course entities.
     */
    @Override
    public long count() {
        return super.count();
    }

    /**
     * Deletes a Course entity, requiring the user to have the necessary permissions.
     *
     * @param toDelete The Course entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Course entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_COURSES.getAuthority())")
    public Course delete(Course toDelete, User requester) {
        return super.delete(toDelete, requester);
    }

    /**
     * Deletes a Course entity by its ID, requiring the user to have the necessary permissions.
     *
     * @param toDeleteId The ID of the Course entity to be deleted.
     * @param requester The user initiating the delete action.
     * @return The Course entity after deletion.
     */
    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_COURSES.getAuthority())")
    public Course delete(long toDeleteId, User requester) {
        return super.delete(toDeleteId, requester);
    }
}
