package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.course.Grade;
import com.example.schooljournal.data.entity.user.Teacher;
import com.example.schooljournal.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
public class TeacherEndpoint extends GenericEndpoint {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Teacher>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        return ResponseEntity.ok(teacherService.getAll(mapToPage(pageNo, pageSize, sortBy)));
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<Teacher> save(@RequestBody() Teacher toSave) {
        return ResponseEntity.ok(teacherService.save(toSave, getCurrentUser()));
    }
}
