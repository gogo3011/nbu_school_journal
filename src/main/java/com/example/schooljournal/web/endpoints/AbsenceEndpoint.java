package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.course.Absence;
import com.example.schooljournal.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/absence")
public class AbsenceEndpoint extends GenericEndpoint {

    @Autowired
    private AbsenceService absenceService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Absence>> list(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(absenceService.getAll(mapToPage(pageNo, pageSize, sortBy)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Absence> save(
            @RequestBody Absence toSave
    ) {
        return ResponseEntity.ok(absenceService.save(toSave, getCurrentUser()));
    }

}
