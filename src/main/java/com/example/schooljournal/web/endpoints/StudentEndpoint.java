package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.user.Director;
import com.example.schooljournal.data.entity.user.Student;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.services.DirectorService;
import com.example.schooljournal.services.StudentService;
import com.example.schooljournal.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/student")
public class StudentEndpoint extends GenericEndpoint {

    @Autowired
    private StudentService studentService;
    @Autowired
    private DirectorService directorService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Student>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(studentService.getAll(mapToPage(pageNo, pageSize, sortBy)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student, getCurrentUser()));
    }

    @GetMapping(path = "/my")
    public ResponseEntity<List<Student>> listMyStudents(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        User currentUser = getCurrentUser();
        List<Student> result = null;
        switch (currentUser.getSchoolRole()) {
            case TEACHER -> {
                result = studentService.getStudentsForTeacherId(CollectionUtils.toSet(currentUser.getId()), mapToPage(pageNo, pageSize, sortBy));
            }
            case DIRECTOR -> {
                Director director = directorService.load(currentUser.getId());
                result = studentService.getStudentsForSchoolId(CollectionUtils.toSet(director.getSchool().getId()), mapToPage(pageNo, pageSize, sortBy));
            }
            case STUDENT -> {
                result = Arrays.asList(studentService.load(currentUser.getId()));
            }
            case PARENT -> {
                result = studentService.getStudentsForParentIds(CollectionUtils.toSet(currentUser.getId()), mapToPage(pageNo, pageSize, sortBy));
            }
            case ADMIN -> {
                result = studentService.getAll(mapToPage(pageNo, pageSize, sortBy));
            }
        }
        if (null != result) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
