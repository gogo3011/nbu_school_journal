package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.user.Director;
import com.example.schooljournal.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/director")
public class DirectorEndpoint extends GenericEndpoint {

    @Autowired
    private DirectorService directorService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Director>> list(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(directorService.getAll(mapToPage(pageNo, pageSize, sortBy)));

    }

    @PostMapping(path = "/save")
    public ResponseEntity<Director> save(@RequestBody Director director) {
        return ResponseEntity.ok(directorService.save(director, getCurrentUser()));
    }
}
