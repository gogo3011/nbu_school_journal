package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.repository.SchoolRepository;
import com.example.schooljournal.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/school")
public class SchoolEndpoint extends GenericEndpoint {

    @Autowired
    private SchoolService schoolService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<School>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(schoolService.getAll(mapToPage(pageNo, pageSize, sortBy)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<School> save(@RequestBody School save) {
        return ResponseEntity.ok(schoolService.save(save, getCurrentUser()));
    }

}
