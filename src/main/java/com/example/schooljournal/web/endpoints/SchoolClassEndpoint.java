package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.Director;
import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.services.DirectorService;
import com.example.schooljournal.services.ParentService;
import com.example.schooljournal.services.SchoolClassService;
import com.example.schooljournal.services.StudentService;
import com.example.schooljournal.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/schoolClass")
public class SchoolClassEndpoint extends GenericEndpoint {

    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<SchoolClass>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(schoolClassService.getAll(mapToPage(pageNo, pageSize, sortBy)));
    }

    @GetMapping(path = "/my")
    public ResponseEntity<List<SchoolClass>> listMySchoolClasses(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        User currentUser = getCurrentUser();
        switch (currentUser.getSchoolRole()){
            case TEACHER -> {
                return ResponseEntity.ok(schoolClassService.listSchoolClassForClassLeader(currentUser.getId(), mapToPage(pageNo, pageSize, sortBy)));
            }
            case DIRECTOR -> {
                Director director = directorService.load(currentUser.getId());
                return ResponseEntity.ok(schoolClassService.listSchoolClassForSchoolId(director.getSchool().getId(), mapToPage(pageNo, pageSize, sortBy)));
            }
            case STUDENT -> {
                return ResponseEntity.ok(schoolClassService.listSchoolClassForStudentIds(CollectionUtils.toSet(currentUser.getId()), mapToPage(pageNo, pageSize, sortBy)));
            }
            case PARENT -> {
                Set<Long> studentIds = studentService.getStudentsForParentIds(CollectionUtils.toSet(currentUser.getId()), Pageable.unpaged())
                        .stream().map(User::getId).collect(Collectors.toSet());
                return ResponseEntity.ok(schoolClassService.listSchoolClassForStudentIds(studentIds, mapToPage(pageNo, pageSize, sortBy)));
            }
            case ADMIN -> {
                return ResponseEntity.ok(schoolClassService.getAll(mapToPage(pageNo, pageSize, sortBy)));
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<SchoolClass> save(@RequestBody SchoolClass schoolClass) {
        return ResponseEntity.ok(schoolClassService.save(schoolClass, getCurrentUser()));
    }
}
