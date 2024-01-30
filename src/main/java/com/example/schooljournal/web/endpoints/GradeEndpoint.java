package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.course.Grade;
import com.example.schooljournal.data.entity.user.Director;
import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.data.entity.user.Teacher;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.services.*;
import com.example.schooljournal.services.exceptions.UnauthorizedAction;
import com.example.schooljournal.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/grade")
public class GradeEndpoint extends GenericEndpoint {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private ParentService parentService;


    @GetMapping(path = "/forCourse")
    public ResponseEntity<List<Grade>> forCourse(
            @RequestParam(required = true) Long courseId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        User currentUser = getCurrentUser();
        try {
            return ResponseEntity.ok(gradeService.getGradesForCourse(courseId, currentUser, mapToPage(pageNo, pageSize, sortBy)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Grade>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        return ResponseEntity.ok(gradeService.getAll(mapToPage(pageNo, pageSize, sortBy)));
    }

    @GetMapping(path = "/my")
    public ResponseEntity<List<Grade>> listMy(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        User currentUser = getCurrentUser();
        switch (currentUser.getSchoolRole()) {
            case TEACHER -> {
                return ResponseEntity.ok(gradeService.getGradesByCourseIds(
                        courseService.getCoursesForTeacherId(
                                currentUser.getId(), Pageable.unpaged()).stream().map(Course::getId).collect(Collectors.toSet()),
                        mapToPage(pageNo, pageSize, sortBy)));
            }
            case DIRECTOR -> {
                Director director = directorService.load(currentUser.getId());
                return ResponseEntity.ok(gradeService.getGradesByCourseIds(
                        director.getSchool().getClasses().stream().flatMap(schoolClass -> schoolClass.getTaughtCourses().stream()).map(Course::getId).collect(Collectors.toSet()),
                        mapToPage(pageNo, pageSize, sortBy)));
            }
            case STUDENT -> {
                return ResponseEntity.ok(gradeService.getGradesByStudentIds(CollectionUtils.toSet(currentUser.getId()), mapToPage(pageNo, pageSize, sortBy)));
            }
            case PARENT -> {
                Parent parent = parentService.load(currentUser.getId());
                return ResponseEntity.ok(gradeService.getGradesByStudentIds(parent.getStudents().stream().map(User::getId).collect(Collectors.toSet()), mapToPage(pageNo, pageSize, sortBy)));
            }
            case ADMIN -> {
                return ResponseEntity.ok(gradeService.getAll(mapToPage(pageNo, pageSize, sortBy)));
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "/save")
    public ResponseEntity<Grade> save(
            @RequestBody() Grade grade
    ) {
        try {
            return ResponseEntity.ok(gradeService.save(grade, getCurrentUser()));
        } catch (UnauthorizedAction e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
