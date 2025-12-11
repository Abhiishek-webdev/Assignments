package com.abhishek.assignment.controller;

import com.abhishek.assignment.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AccessController {

    @Autowired
    private AccessService accessService;

    // GET /courses/{courseId}/lessons
    @GetMapping("/courses/{courseId}/lessons")
    public ResponseEntity<List<String>> getLessons(
            @PathVariable Long courseId, 
            @RequestParam Long userId) { // Passing userId as param for simplicity
        
        return ResponseEntity.ok(accessService.getLessons(courseId, userId));
    }
}
