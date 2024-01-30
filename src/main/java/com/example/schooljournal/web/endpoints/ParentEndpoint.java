package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.user.Parent;
import com.example.schooljournal.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/parent")
public class ParentEndpoint extends GenericEndpoint {

    @Autowired
    private ParentService parentService;

    @PostMapping(path = "save")
    public ResponseEntity<Parent> save(@RequestBody Parent parent) {
        return ResponseEntity.ok(parentService.save(parent, getCurrentUser()));
    }

    @GetMapping(path = "list")
    public ResponseEntity<List<Parent>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(parentService.getAll(mapToPage(pageNo, pageSize, sortBy)));
    }
}
