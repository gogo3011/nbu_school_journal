package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.config.Permission;
import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.user.Student;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.services.CourseService;
import com.example.schooljournal.services.DirectorService;
import com.example.schooljournal.services.ParentService;
import com.example.schooljournal.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/course")
public class CourseEndpoint extends GenericEndpoint {
    @Autowired
    private CourseService courseService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private ParentService parentService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Course>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        List<Course> result = courseService.getAll(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/my")
    public ResponseEntity<List<Course>> listMyCourses(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        User currentUser = getCurrentUser();
        List<Course> result = null;
        switch (currentUser.getSchoolRole()) {
            case TEACHER -> {
                result = courseService.getCoursesForTeacherId(currentUser.getId(), mapToPage(pageNo, pageSize, sortBy));
            }
            case DIRECTOR -> {
                result = courseService.getCoursesForSchoolId(directorService.load(currentUser.getId()).getSchool().getId(), mapToPage(pageNo, pageSize, sortBy));
            }
            case STUDENT -> {
                result = courseService.getCoursesForStudentId(currentUser.getId(), mapToPage(pageNo, pageSize, sortBy));
            }
            case PARENT -> {
                result = courseService.getCoursesForParentIds(
                        CollectionUtils.toSet(currentUser.getId()),
                        mapToPage(pageNo, pageSize, sortBy)
                );
            }
            case ADMIN -> {
                result = courseService.getAll(mapToPage(pageNo, pageSize, sortBy));
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Course> save(
            @RequestBody Course toSave
    ) {
        return ResponseEntity.ok(courseService.save(toSave, getCurrentUser()));
    }
}
